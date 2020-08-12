ARG baseJdk=pi4k8s/oraclejdk:8u201-jdk-ubuntu-18-04

FROM $baseJdk

ARG TOMCAT_VERSION=9.0.37

RUN cd /opt && apt update && apt install -y wget \
    && wget -q "https://mirrors.tuna.tsinghua.edu.cn/apache/tomcat/tomcat-9/v$TOMCAT_VERSION/bin/apache-tomcat-$TOMCAT_VERSION.tar.gz" \
    && tar -zxf apache-tomcat-$TOMCAT_VERSION.tar.gz \
    && rm -rf apache-tomcat-$TOMCAT_VERSION.tar.gz \
    && mv /opt/apache-tomcat* /opt/tomcat9 \
    && rm -rf /opt/tomcat9/webapps/examples \
    && apt remove -y --purge wget && apt clean \
    && rm -rf /tmp/* /var/cache/* /usr/share/doc/* /usr/share/man/* /var/lib/apt/lists/*

# arm64v8
# docker build . -t pi4k8s/tomcat9:9.0.37-8u201-jdk-ubuntu-18-04

# x86
# docker build . -t fastjrun/tomcat9:9.0.37-8u201-jdk-ubuntu-18-04 --build-arg baseJdk=fastjrun/oraclejdk:8u201-jdk-ubuntu-18-04