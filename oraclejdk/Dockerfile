ARG version=18.04
ARG baseUbuntu=pi4k8s/ubuntu
FROM $baseUbuntu:$version
ARG JDK_VERSION=8u201
ARG JDK_SUFFIX=arm64-vfp-hflt

RUN cd /opt && apt update && apt install -y wget \
    && wget -q "https://github.com/fastjrun/soft/releases/download/oraclejdk-$JDK_VERSION/jdk-$JDK_VERSION-linux-$JDK_SUFFIX.tar.gz" \
    && tar -zxf jdk-$JDK_VERSION-linux-$JDK_SUFFIX.tar.gz \
    && rm -rf jdk-$JDK_VERSION-linux-$JDK_SUFFIX.tar.gz \
    && mv /opt/jdk* /opt/jdk \
    && apt remove -y --purge wget && apt clean \
    && rm -rf /tmp/* /var/cache/* /usr/share/doc/* /usr/share/man/* /var/lib/apt/lists/*

ENV JAVA_HOME=/opt/jdk
ENV PATH=$JAVA_HOME/bin:$PATH

# arm64v8
# docker build . -t pi4k8s/oraclejdk:8u201-jdk-ubuntu-18-04

# x86
# docker build . -t fastjrun/oraclejdk:8u201-jdk-ubuntu-18-04 --build-arg baseUbuntu=fastjrun/ubuntu  --build-arg version=18.04 --build-arg JDK_SUFFIX=x64