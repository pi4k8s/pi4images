FROM pi4k8s/openjdk8:centos7

ARG JENKINS_VERSION=2.327

ARG CPU_ARCH=x64

ENV JAVA_OPTS='-Xmx1024m -Xms1024m'

ADD https://dlcdn.apache.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.tar.gz /opt

ADD https://nodejs.org/dist/v14.17.4/node-v14.17.4-linux-$CPU_ARCH.tar.xz /opt

RUN  cd /opt && yum install -y curl git \
     && curl -O -L -s "https://get.jenkins.io/war/$JENKINS_VERSION/jenkins.war" \
     && mv node-v14.17.4-linux-$CPU_ARCH nodejs \
     && mv apache-maven-3.8.4 maven \
     && yum clean all && rm -rf /var/cache/yum/* \

ENV PATH="/opt/maven/bin:/opt/nodejs/bin:${PATH}"

ENTRYPOINT java $JAVA_OPTS -jar /opt/jenkins.war

VOLUME /root