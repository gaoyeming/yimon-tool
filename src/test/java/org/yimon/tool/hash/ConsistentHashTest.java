package org.yimon.tool.hash;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ConsistentHashTest {

    @Test
    public void segmentTest() {
        ConsistentHash consistentHash = new ConsistentHash(Arrays.asList("01", "02", "03", "04"));

        List<String> keyList = Arrays.asList("ec683a77-5221-411e-ac8e-7c7921a4424d", "c4e6bb56-9378-414d-99f5-dadaef0769a4", "429a300d-a5b5-4892-ae51-868fe414ee74", "f839c75c-e87e-47bc-ad54-321de8a53feb");
        for (String key : keyList) {
            System.out.println(key + ":to hash-->" + HashHandler.FNV1Hash(key));
            System.out.println(key + ":to node-->" + consistentHash.mappingNode(key));
        }

        consistentHash.dropNode("03");
        System.out.println("-------> dropNode 03");
        for (String key : keyList) {
            System.out.println(key + ":to hash-->" + HashHandler.FNV1Hash(key));
            System.out.println(key + ":to node-->" + consistentHash.mappingNode(key));
        }

        consistentHash.addNode("05");
        System.out.println("-------> addNode 05");
        for (String key : keyList) {
            System.out.println(key + ":to hash-->" + HashHandler.FNV1Hash(key));
            System.out.println(key + ":to node-->" + consistentHash.mappingNode(key));
        }

        consistentHash.addNode("03");
        System.out.println("-------> addNode 03");
        for (String key : keyList) {
            System.out.println(key + ":to hash-->" + HashHandler.FNV1Hash(key));
            System.out.println(key + ":to node-->" + consistentHash.mappingNode(key));
        }
    }

}