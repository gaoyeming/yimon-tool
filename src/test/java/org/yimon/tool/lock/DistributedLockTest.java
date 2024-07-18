package org.yimon.tool.lock;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: ym.gao
 * @description: TODO
 * @date: 2024/2/4 11:16
 */
public class DistributedLockTest {

    private static final JedisPoolConfig poolConfig = new JedisPoolConfig();
    // 创建 Jedis 连接池
    private static final JedisPool jedisPool;
    private static final ExecutorService executorService = new ThreadPoolExecutor(8, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10000));
    private static final DistributedLock lock = new DistributedLock("Test:key", new TryAcquire() {
        @Override
        public Boolean carry(String key, String value, long expireTime, TimeUnit unit) {
            expireTime = unit.toSeconds(expireTime);
            List<String> KEYS = Collections.singletonList(key);
            List<String> ARGV = Arrays.asList(value, String.valueOf(expireTime));
            //执行脚本
            try (Jedis jedis = jedisPool.getResource()) {
                Object result = jedis.eval(DistributedLockTest.readScriptFromFile("redis_lock_script.lua"), KEYS, ARGV);
                return result != null && "1".equals(String.valueOf(result));
            } catch (IOException e) {
                return Boolean.FALSE;
            }
        }
    }, new TryRelease() {
        @Override
        public Boolean carry(String key, String value) {
            List<String> KEYS = Collections.singletonList(key);
            List<String> ARGV = Collections.singletonList(value);
            //执行脚本
            try (Jedis jedis = jedisPool.getResource()) {
                Object result = jedis.eval(DistributedLockTest.readScriptFromFile("redis_unlock_script.lua"), KEYS, ARGV);
                return result != null && "1".equals(String.valueOf(result));
            } catch (IOException e) {
                return Boolean.FALSE;
            }
        }
    });
    private static Integer count = 0;

    static {
        poolConfig.setMaxTotal(10); // 设置最大连接数
        poolConfig.setMaxIdle(5); // 设置最大空闲连接数
        jedisPool = new JedisPool(poolConfig, "localhost", 6379);
    }

    private static String readScriptFromFile(String fileName) throws IOException {
        URL in = ClassLoader.getSystemClassLoader().getResource(fileName);
        try (FileInputStream fis = new FileInputStream(in.getPath())) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            StringBuilder sb = new StringBuilder();
            while ((bytesRead = fis.read(buffer)) != -1) {
                String fileContent = new String(buffer, 0, bytesRead);
                sb.append(fileContent);
            }
            return sb.toString();
        } catch (IOException e) {
            return null;
        }
    }

    @Test
    public void distributedLockTest() {
        Jedis jedis = jedisPool.getResource();
        jedis.del("Test:key");
        for (int i = 0; i < 500; i++) {
            executorService.execute(new TaskTest());
        }
        executorService.shutdown();
        //进行阻塞
        while (!executorService.isTerminated()) {
        }
        System.out.println("count--->" + count);
        jedis.close();
    }

    static class TaskTest implements Runnable {

        @Override
        public void run() {
            if (lock.tryLock(1, 100L, TimeUnit.SECONDS)) {
                count++;
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                System.out.println("count++");
                lock.unlock();
            }
        }
    }
}
