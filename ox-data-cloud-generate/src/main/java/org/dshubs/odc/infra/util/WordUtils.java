package org.dshubs.odc.infra.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符操作工具类型
 *
 * @author wangxian 2021/3/17
 */
public class WordUtils {
    /**
     * 首字母转大写 如userName 转UserName
     *
     * @param word 单词
     * @return 首字母大写的单词
     */
    public static String firstWordToUpperCase(String word) {
        String firstWord = StringUtils.substring(word, 0, 1);
        return firstWord.toUpperCase() + StringUtils.substring(word, 1, word.length());
    }

    /**
     * 首字母转小写,UserName转userName
     *
     * @param word 单词
     * @return 首字母小写的单词
     */
    public static String firstWordToLowerCase(String word) {
        String firstWord = StringUtils.substring(word, 0, 1);
        return firstWord.toLowerCase() + StringUtils.substring(word, 1, word.length());
    }

    /**
     * 将单词根据指定字符转驼峰并且首字母小写,如将user_name转成userName
     *
     * @param word 单词
     * @param key  字符,如_
     * @return String
     */
    public static String toCamelCaseFirstLower(String word, String key) {
        return firstWordToLowerCase(toCamelCaseFirstUpper(word, key));
    }

    /**
     * 将单词根据指定字符转驼峰并且首字母大写,如将user_name转成UserName
     *
     * @param word 单词
     * @param key  字符,如_
     * @return String
     */
    public static String toCamelCaseFirstUpper(String word, String key) {
        String[] words = StringUtils.split(word, key);
        StringBuilder result = new StringBuilder();
        for (String str : words) {
            if (StringUtils.isNotBlank(str)) {
                result.append(firstWordToUpperCase(str));
            }
        }
        return result.toString();
    }
}
