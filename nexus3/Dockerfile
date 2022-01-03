FROM pi4k8s/openjdk8:centos7

ARG NEXUS_VERSION=3.37.3-02

ADD start_nexus.sh /opt

ENV JVM_MINIMUM_MEMORY=1024M

ENV JVM_MAXIMUM_MEMORY=1560M

ENV JVM_MAXDIRECTMEMORYSIZE=1024M

RUN cd /opt && yum install -y curl \
    && curl -O -L -s "https://download.sonatype.com/nexus/3/nexus-$NEXUS_VERSION-unix.tar.gz" \
    && tar -zxf "nexus-$NEXUS_VERSION-unix.tar.gz" \
    && rm -rf "nexus-$NEXUS_VERSION-unix.tar.gz" \
    && mv /opt/nexus-3* /opt/nexus-3 \
    && sed -i 1,4d /opt/nexus-3/bin/nexus.vmoptions \
    && mv /opt/nexus-3/bin/nexus.vmoptions /opt/nexus-3/bin/nexus.vmoptions.tmp \
    && chmod a+x /opt/start_nexus.sh \
    && yum clean all && rm -rf /var/cache/yum/*

ENTRYPOINT  /opt/start_nexus.sh

VOLUME /opt/sonatype-work

# arm64
# docker build . -t pi4k8s/nexus3-arm64:openjdk8 && docker push pi4k8s/nexus3-arm64:openjdk8

# amd64
# docker build . -t pi4k8s/nexus3-amd64:openjdk8 && docker push pi4k8s/nexus3-amd64:openjdk8

# manifest
# docker manifest create pi4k8s/nexus3:openjdk8 pi4k8s/nexus3-amd64:openjdk8 pi4k8s/nexus3-arm64:openjdk8
# docker manifest annotate pi4k8s/nexus3:openjdk8 pi4k8s/nexus3-amd64:openjdk8 --os linux --arch amd64
# docker manifest annotate pi4k8s/nexus3:openjdk8 pi4k8s/nexus3-arm64:openjdk8 --os linux --arch arm64
# docker manifest push pi4k8s/nexus3:openjdk8