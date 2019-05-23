
FROM openjdk:8-jdk-alpine
##指定维护者信息
MAINTAINER wxhao
#这里的 /tmp 目录就会在运行时自动挂载为匿名卷，任何向 /tmp 中写入的信息都不会记录进容器存储层
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENV LANG zh_CN.utf8
# ENTRYPOINT 为容器启动后执行的命令
#ENTRYPOINT ["java","-Dspring.datasource.url=jdbc:mysql://dev-mysql:3306/everyday?characterEncoding=utf-8&useSSL=false","-cp","app:app/lib/*","com.wxhao.blog.server.BlogHelperServerApplication"]
