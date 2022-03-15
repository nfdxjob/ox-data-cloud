package org.dshubs.odc.infra.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 域账号生成工具类
 *
 * @author create by wangxian 2022/3/15
 */
public class EmployeeDomainAccountUtils {

    private static final HanyuPinyinOutputFormat FORMAT = new HanyuPinyinOutputFormat();


    private EmployeeDomainAccountUtils() {
    }

    public static String generateDomainAccount(String domainAccount) throws BadHanyuPinyinOutputFormatCombination {
        char[] chars = domainAccount.toCharArray();
        StringBuilder result = new StringBuilder();
        int size = chars.length;
        for (int i = 0; i < size; i++) {
            char tmp = chars[i];
            if (size > 2 & i > 0) {
                result.append(toInitials(tmp));
            } else {
                result.append(toPinyin(tmp));
            }
        }

        return result.toString();

    }

    private static String toPinyin(char s) throws BadHanyuPinyinOutputFormatCombination {
        String result = PinyinHelper.toHanYuPinyinString(s + "", FORMAT, "", true);
        if (result.length() > 1) {
            return result.substring(0, result.length() - 1);
        }
        return result;
    }

    private static String toInitials(char s) throws BadHanyuPinyinOutputFormatCombination {
        String result = PinyinHelper.toHanYuPinyinString(s + "", FORMAT, "", true);
        if (result.length() > 1) {
            return result.substring(0, 1);
        }
        return result;
    }
}
