ARG baseJdk=pi4k8s/oraclejdk:8u201-jdk-ubuntu-18-04

FROM $baseJdk

ARG JMETER_VERSION=5.4.1

RUN cd /opt && apt update && apt install -y wget \
    && wget -q "https://archive.apache.org/dist/jmeter/binaries/apache-jmeter-$JMETER_VERSION.tgz" \
    && tar -zxf apache-jmeter-$JMETER_VERSION.tgz \
    && rm -rf apache-jmeter-$JMETER_VERSION.tgz \
    && mv /opt/apache-jmeter* /opt/jmeter5 \
    && apt remove -y --purge wget && apt clean \
    && rm -rf /tmp/* /var/cache/* /usr/share/doc/* /usr/share/man/* /var/lib/apt/lists/*

ADD https://github.com/fastjrun/soft/releases/download/mysql-connector-java-5.1.47/mysql-connector-java-5.1.47.jar /opt/jmeter5/lib

# arm64v8
# docker build . -t pi4k8s/jmeter5:5.4.1-8u201-jdk-ubuntu-18-04

# x86
# docker build . -t fastjrun/jmeter5:5.4.1-8u201-jdk-ubuntu-18-04 --build-arg baseJdk=fastjrun/oraclejdk:8u201-jdk-ubuntu-18-04