FROM pi4k8s/openjdk8:centos7

ARG JMETER_VERSION=5.4.1

RUN cd /opt && yum install -y wget \
    && wget -q "https://archive.apache.org/dist/jmeter/binaries/apache-jmeter-$JMETER_VERSION.tgz" \
    && tar -zxf apache-jmeter-$JMETER_VERSION.tgz \
    && rm -rf apache-jmeter-$JMETER_VERSION.tgz \
    && mv /opt/apache-jmeter* /opt/jmeter5 \
    && yum remove -y wget && yum clean all && rm -rf /var/cache/yum/*

ADD https://github.com/fastjrun/soft/releases/download/mysql-connector-java-5.1.47/mysql-connector-java-5.1.47.jar /opt/jmeter5/lib

# arm64v8
# docker build . -t pi4k8s/jmeter5-arm64:openjdk8 && docker push pi4k8s/jmeter5-arm64:openjdk8

# x86
# docker build . -t pi4k8s/jmeter5-amd64:openjdk8 && docker push pi4k8s/jmeter5-amd64:openjdk8

# manifest
# docker manifest create pi4k8s/jmeter5:openjdk8 pi4k8s/jmeter5-amd64:openjdk8 pi4k8s/jmeter5-arm64:openjdk8
# docker manifest annotate pi4k8s/jmeter5:openjdk8 pi4k8s/jmeter5-amd64:openjdk8 --os linux --arch amd64
# docker manifest annotate pi4k8s/jmeter5:openjdk8 pi4k8s/jmeter5-arm64:openjdk8 --os linux --arch arm64
# docker manifest push pi4k8s/jmeter5:openjdk8