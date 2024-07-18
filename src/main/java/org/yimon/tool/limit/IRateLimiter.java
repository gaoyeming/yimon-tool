package org.yimon.tool.limit;

public interface IRateLimiter {

    boolean isAllow(String resource);

}
