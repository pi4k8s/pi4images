ARG version=8u201-jdk-ubuntu-18-04
ARG baseOracleJdk=pi4k8s/oraclejdk

FROM $baseOracleJdk:$version

ENV JAVA_OPTS='-Xmx1024m -Xms1024m'

RUN  cd /opt && apt update && apt install -y curl \
     && curl -sSL https://zipkin.io/quickstart.sh | bash -s \
     && apt remove -y --purge wget && apt clean \
     && rm -rf /tmp/* /var/cache/* /usr/share/doc/* /usr/share/man/* /var/lib/apt/lists/*

ENTRYPOINT java $JAVA_OPTS -jar /opt/zipkin.jar

# arm64v8
# docker build . -t pi4k8s/zipkin

# x86
# docker build . -t fastjrun/zipkin --build-arg baseOracleJdk=fastjrun/oraclejdk