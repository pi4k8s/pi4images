ARG baseOracleJDK=pi4k8s/oraclejdk:8u201-jdk-ubuntu-18-04
FROM $baseOracleJDK

ADD config.yaml /opt

RUN cd /opt && apt update && apt install -y wget \
    && wget -q "https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.13.0/jmx_prometheus_javaagent-0.13.0.jar" \
    && apt remove -y --purge wget && apt clean \
    && rm -rf /tmp/* /var/cache/* /usr/share/doc/* /usr/share/man/* /var/lib/apt/lists/*


# arm64v8
# docker build . -t pi4k8s/oraclejdk-jmx:8u201-jdk-ubuntu-18-04

# x86
# docker build . -t fastjrun/oraclejdk-jmx:8u201-jdk-ubuntu-18-04 --build-arg baseOracleJDK=fastjrun/oraclejdk:8u201-jdk-ubuntu-18-04