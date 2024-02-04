package com.yimon.core.lock;

import com.yimon.core.lock.script.LuaScript;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

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

    private static Integer count = 0;

    private static final Jedis jedis;

    static {
        jedis = new Jedis("localhost", 6379, 50000);
    }

    private static final ExecutorService executorService = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10000));

    private static final DistributedLock lock = new DistributedLock("Test:key", new TryAcquire() {
        @Override
        public Boolean carry(String key, String value, long expireTime, TimeUnit unit) {
//            expireTime = unit.toSeconds(expireTime);
//            List<String> KEYS = Collections.singletonList(key);
//            List<String> ARGV = Arrays.asList(value, String.valueOf(expireTime));
//            //执行脚本
//            Object result = jedis.eval(LuaScript.lockScript(), KEYS, ARGV);
//            System.out.println("TryAcquire");
//
//            ;

            return jedis.setnx(key, value) == 1L;
        }
    }, new TryRelease() {
        @Override
        public Boolean carry(String key, String value) {
            List<String> KEYS = Collections.singletonList(key);
            List<String> ARGV = Collections.singletonList(value);
            //执行脚本
            Object result = jedis.eval(LuaScript.unlockScript(), KEYS, ARGV);
            System.out.println("TryRelease");

            return result == null || "1".equals(String.valueOf(result));
        }
    });

    public static void main(String[] args) {
        jedis.del("Test:key");
        for (int i = 0; i < 2; i++) {
            executorService.execute(new DistributedLockTest.TaskTest());
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
            if (lock.tryLock(10L, 100L, TimeUnit.SECONDS)) {
                count++;
                System.out.println("count++");
                lock.unlock();
            }

        }
    }
}
