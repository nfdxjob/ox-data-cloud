package org.dshubs.odc.core.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author create by wangxian 2022/2/21
 */
public class BigDecimalTest {
    @Test
    public void test1() {
        computerBigDecimal(new BigDecimal("1.1"));
        computerBigDecimal(new BigDecimal("1.5"));
        computerBigDecimal(new BigDecimal("1.49"));
        computerBigDecimal(new BigDecimal("1.6"));
        computerBigDecimal(new BigDecimal("1.51"));
        computerBigDecimal(new BigDecimal("1.7"));
    }

    public static void computerBigDecimal(BigDecimal bigDecimal) {
        //取出小数
        BigDecimal decimal = bigDecimal.remainder(BigDecimal.ONE);
        //向下取整
        BigDecimal result = bigDecimal.setScale(0, RoundingMode.DOWN);
        if (decimal.compareTo(new BigDecimal("0.5")) < 1) {
            result = result.add(new BigDecimal("0.5"));
        } else {
            result = result.add(new BigDecimal("1"));
        }
        System.out.println(bigDecimal + "进过计算值为:" + result);
    }
}
