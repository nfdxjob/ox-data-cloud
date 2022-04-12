# 牛数企业级快速开发框架(ox-data-cloud)

[TOC]

### 简介

**ox-data-cloud,以下简称odc用于企业解决项目开发微服务框架搭建需求,提供快速开发平台,为公司节省开发成本,让开发人员只需要关心具体业务开发**

### 目录结构
```
ox-data-cloud/
  ox-data-cloud-admin/
  ox-data-cloud-document
  ox-data-cloud-file
  ox-data-cloud-gateway
  ox-data-cloud-generate
  ox-data-cloud-import
  ox-data-cloud-message
  ox-data-cloud-module(用于提供可打包的模块功能)
    ox-data-cloud-audit(审计)
    ox-data-cloud-base-message(提供对外消息能力)
    ox-data-cloud-base-user
    ox-data-cloud-core
    ox-data-cloud-lock
    ox-data-cloud-mybatis
    ox-data-cloud-redis
  ox-data-cloud-oauth
  ox-data-cloud-parent
  ox-data-cloud-platform
  ox-data-cloud-register
  ox-data-cloud-workflow

```

### 服务预览

|        服务名称        | 端口 | 描述 | 数据库 |
| :--------------------: | :--: | :--: | :----: |
| ox-data-cloud-platform |      |      |        |
|  ox-data-cloud-oauth   |      |      |        |
| ox-data-cloud-message  |      |      |        |
| ox-data-cloud-document |      |      |        |
|   ox-data-cloud-file   |      |      |        |
| ox-data-cloud-workflow |      |      |        |
|  ox-data-cloud-gateay  |      |      |        |



### 快速使用

- 获取源代码

  ```shell
  git clone https://github.com/ox-data/ox-data-cloud.git
  ```

- 编译代码

  ```shell
  # 进入父级模块
  $: cd ox-data-cloud-parent
  # 编译
  $: mvn clean install -Dmaven.test.skip
  # 回到主目录
  $: cd ..
  # 编译所有模块
  $: mvn clean install -Dmaven.test.skip
  ```

### 技术架构

[架构设计](https://bgdj0gqgw8.feishu.cn/mindnotes/bmncnR78tyssUOqd6KMiO7Cneld)

### 技术使用

- 基础框架 Spring Boot 2.4.12
- 微服务框架 Spring Cloud 2020.0.4
  - Gateway,Feign
- 持久层框架 Mybatis Plus 3.5.1
- 安全框架 Spring Security,Oauth2
- 缓存 Redis
- 数据库 MySQL 5.7
- 其他依赖 logback, common-lang3, google-guava,swagger,lombok

### 开发文档

[开发文档](https://bgdj0gqgw8.feishu.cn/docs/doccnmJyHjwU7vGpm79xYQ7r001)

