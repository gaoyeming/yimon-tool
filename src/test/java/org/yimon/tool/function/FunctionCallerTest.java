package org.yimon.tool.function;

import org.junit.Test;
import org.yimon.tool.util.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;

public class FunctionCallerTest {

    @Test
    public void callTest(){
        Calendar a = FunctionCaller.call("a","b", new Date(), DateFormatUtils::date2Calendar, true);
    }

}