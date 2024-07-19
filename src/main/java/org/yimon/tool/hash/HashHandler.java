package org.yimon.tool.hash;

/**
 * @author: ym.gao
 * @description: 哈希算法
 * @date: 2024/7/19 下午4:09
 */
public class HashHandler {

    private HashHandler() {
    }

    /**
     * FNV1_32_HASH算法
     *
     * @param key 需要计算的key
     * @return 计算后的值
     */
    public static int FNV1Hash(String key) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++)
            hash = (hash ^ key.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }
}
