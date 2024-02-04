package com.yimon.core.lock.script;

/**
 * @author: ym.gao
 * @description: lua脚本
 * @date: 2024/2/2 14:33
 */
public class LuaScript {

    private LuaScript() {

    }

    /**
     * 分布式锁加锁锁lua脚本
     * KEYS[1]:对应锁的key
     * ARGV[1]:对应锁的value
     * ARGV[2]:对应锁的过期时间
     *
     * @return 当且仅当返回 `1`才表示加锁成功. '0'表示当前线程加锁失败
     */
    public static String lockScript() {
        return "if redis.call('setnx', KEYS[1], ARGV[1])  == 1 then " + //setnx进行加锁
                "    return redis.call('expire', KEYS[1], ARGV[2]);" + //成功设置锁的过期时间
                "end;" +
                "if redis.call('get', KEYS[1]) == ARGV[1] then " + //加锁失败，说明锁已经存在，则获取锁；判断锁的值是否属于预期值
                "    return redis.call('expire', key, ARGV[2]);" + //属于则进行锁过期时间刷新
                "else" +
                "   return 0;" +
                "end;";
    }

    /**
     * 分布式锁解锁lua脚本
     * KEYS[1]:对应锁的key
     * ARGV[1]:对应锁的value
     *
     * @return 当且仅当返回 `1`才表示解锁成功. null表示锁过期失效自动释放. '0'表示当前线程不允许解锁
     */
    public static String unlockScript() {
        return "if (redis.call('exists', KEYS[1]) == 0) then " + //判断对应锁是否存在
                "    return nil;" + //不存在返回null
                "end; " +
                "if redis.call('get',KEYS[1]) == ARGV[1] then" + //存在，获取锁；判断锁的值是否属于预期值
                "   return redis.call('del',KEYS[1]);" + //属于则进行删除
                "else" +
                "   return 0;" + //不属于不允许删除
                "end;";
    }
}
