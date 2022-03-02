package org.dshubs.odc.api.vo;

import lombok.Data;

import java.util.List;

/**
 * @author wangxian 2021/3/17
 */
@Data
public class GenerateCodeVO {
    private Config config;

    private List<String> tables;


    @Data
    public static class Config {
        private String author;

        private String packageName;

        /**
         * 是否继承基类
         */
        private Boolean extendsBaseEntity;

        /**
         * 前缀
         */
        private String prefix;
    }
}
