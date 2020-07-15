ARG version=8u201-jdk-ubuntu-18-04
ARG baseOracleJdk=pi4k8s/oraclejdk

FROM $baseOracleJdk:$version

ARG JENKINS_VERSION=2.222.3

ENV JAVA_OPTS='-Xmx1024m -Xms1024m'

RUN  cd /opt && apt update && apt install -y git wget \
     && wget -q "http://mirrors.jenkins.io/war-stable/$JENKINS_VERSION/jenkins.war" \
     && apt remove -y --purge wget && apt clean \
     && rm -rf /tmp/* /var/cache/* /usr/share/doc/* /usr/share/man/* /var/lib/apt/lists/*

ENTRYPOINT java $JAVA_OPTS -jar /opt/jenkins.war

VOLUME /root/.jenkins

# arm64v8
# docker build . -t pi4k8s/jenkins:2.222.3

# x86
# docker build . -t fastjrun/jenkins:2.222.3 --build-arg baseOracleJdk=fastjrun/oraclejdk