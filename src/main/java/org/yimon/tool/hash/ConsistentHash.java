package org.yimon.tool.hash;

import org.yimon.tool.check.Validate;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author: ym.gao
 * @description: 一致性哈希；适用于负载均衡、分库分表
 * @date: 2024/7/19 下午4:20
 */
public class ConsistentHash {

    /**
     * TreeMap记录需要分配的节点信息
     */
    private final SortedMap<Integer, String> sortedMap = new TreeMap<>();

    public ConsistentHash(List<String> initNode){
        Validate.isNonNull(initNode, "node is null in ConsistentHash, not allow init");
        //初始化节点的值
        initNode.forEach(node-> this.sortedMap.put(HashHandler.FNV1Hash(node), node));
    }


    /**
     * 获取分配的节点
     * @param key 对应需要分配的策略
     * @return 获取分配的节点
     */
    public String mappingNode(String key) {
        //得到该key的hash值
        int hash = HashHandler.FNV1Hash(key);
        //得到大于该Hash值的所有Map
        SortedMap<Integer, String> subMap = this.sortedMap.tailMap(hash);
        if(subMap.isEmpty()){
            //如果没有比该key的hash值大的，则从第一个node开始
            Integer i = this.sortedMap.firstKey();
            //返回对应的服务器
            return this.sortedMap.get(i);
        }else{
            //第一个Key就是顺时针过去离node最近的那个结点
            Integer i = subMap.firstKey();
            //返回对应的服务器
            return subMap.get(i);
        }
    }

    public void dropNode(String node) {
        //得到该key的hash值
        int hash = HashHandler.FNV1Hash(node);
        this.sortedMap.remove(hash);
    }

    public void addNode(String node) {
        //得到该key的hash值
        int hash = HashHandler.FNV1Hash(node);
        this.sortedMap.put(hash, node);
    }
}
