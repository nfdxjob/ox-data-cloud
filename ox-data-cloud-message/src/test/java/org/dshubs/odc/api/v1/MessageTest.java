package org.dshubs.odc.api.v1;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

/**
 * @author create by wangxian 2021/12/30
 */
public class MessageTest {

    @Test
    public void dateTest() throws ParseException {
        Date now = new Date();

        System.out.println(now);
        System.out.println(DateFormatUtils.format(now,"yyyy-MM-dd HH:mm:ss"));


        now = DateUtils.parseDate(DateFormatUtils.format(now,"yyyy-MM-dd"),"yyyy-MM-dd");
        System.out.println(now);

        System.out.println(DateFormatUtils.format(now,"yyyy-MM-dd HH:mm:ss"));
    }
}
