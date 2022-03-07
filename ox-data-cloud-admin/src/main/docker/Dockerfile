FROM hub.c.163.com/library/java:latest
MAINTAINER WangXian
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
ENV LANG=zh_CN.UTF-8 \
    LC_ALL=zh_CN.UTF-8
VOLUME /tmp
ENV JAVA_TOOL_OPTIONS -Dfile.encoding=UTF8 -Duser.language=zh -Duser.region=zh_CN -Duser.country=zh_CN
COPY butterfly-register-0.0.1-SNAPSHOT.jar butterfly-register.jar
ENTRYPOINT exec java $JAVA_OPTS  -jar /butterfly-register.jar
