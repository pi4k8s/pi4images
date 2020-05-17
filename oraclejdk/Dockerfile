FROM pi4k8s/ubuntu:18.04

ARG JDK_VERSION=8u201

RUN cd /opt && apt update && apt install -y wget \
    && wget -q "https://github.com/pi4k8s/oraclejdk/releases/download/jdk-$JDK_VERSION/jdk-$JDK_VERSION-linux-arm64-vfp-hflt.tar.gz" \
    && tar -zxf "jdk-$JDK_VERSION-linux-arm64-vfp-hflt.tar.gz" \
    && rm -rf "jdk-$JDK_VERSION-linux-arm64-vfp-hflt.tar.gz" \
    && apt remove -y --purge wget && apt clean \
    && rm -rf /tmp/* /var/cache/* /usr/share/doc/* /usr/share/man/* /var/lib/apt/lists/*

ENV JAVA_HOME=/opt/jdk1.8.0_201
ENV PATH=$JAVA_HOME/bin:$PATH

#docker build . -t pi4k8s/oraclejdk:8u201-jdk-ubuntu-18-04