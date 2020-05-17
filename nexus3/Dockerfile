FROM pi4k8s/oraclejdk:8u201-jdk-ubuntu-18-04

ARG NEXUS_VERSION=3.22.1-02

ADD start_nexus.sh /opt

ENV JVM_MINIMUM_MEMORY=1024M

ENV JVM_MAXIMUM_MEMORY=1560M

ENV JVM_MAXDIRECTMEMORYSIZE=1024M

RUN cd /opt && apt update && apt install -y wget \
    && wget -q "https://github.com/pi4k8s/nexus3/releases/download/3.22.1-02/nexus-$NEXUS_VERSION.tar.gz" \
    && tar -zxf "nexus-$NEXUS_VERSION.tar.gz" \
    && rm -rf "nexus-$NEXUS_VERSION.tar.gz" \
    && mv /opt/nexus-3* /opt/nexus-3 \
    && sed -i 1,4d /opt/nexus-3/bin/nexus.vmoptions \
    && mv /opt/nexus-3/bin/nexus.vmoptions /opt/nexus-3/bin/nexus.vmoptions.tmp \
    && chmod a+x /opt/start_nexus.sh \
    && apt remove -y --purge wget && apt clean \
    && rm -rf /tmp/* /var/cache/* /usr/share/doc/* /usr/share/man/* /var/lib/apt/lists/*

ENTRYPOINT  /opt/start_nexus.sh

VOLUME /opt/sonatype-work

#docker build . -t pi4k8s/nexus3:3.22.1-02