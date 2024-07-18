package org.yimon.tool.function;


import org.yimon.tool.exception.InvokeException;

@FunctionalInterface
public interface FunctionInvoker<T, R> {

    R invoke(T t) throws InvokeException;

}