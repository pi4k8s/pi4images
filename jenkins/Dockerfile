FROM pi4k8s/openjdk8:centos7

ARG JENKINS_VERSION=2.327

ARG CPU_ARCH=x64

ENV JAVA_OPTS='-Xmx1024m -Xms1024m'

RUN  cd /opt && yum install -y curl git make gcc+ gcc-c++ \
     && curl -O -L -s "https://get.jenkins.io/war/$JENKINS_VERSION/jenkins.war" \
     && curl -O -L -s "https://dlcdn.apache.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.tar.gz" \
     && curl -O -L -s "https://nodejs.org/dist/v14.17.4/node-v14.17.4-linux-$CPU_ARCH.tar.xz" \
     && tar -zxf apache-maven-3.8.4-bin.tar.gz && mv apache-maven-3.8.4 maven \
     && tar -xf node-v14.17.4-linux-$CPU_ARCH.tar.xz && mv node-v14.17.4-linux-$CPU_ARCH nodejs \
     && rm -f apache-maven-3.8.4-bin.tar.gz && rm -f node-v14.17.4-linux-$CPU_ARCH.tar.xz \
     && yum clean all && rm -rf /var/cache/yum/*

ENV PATH "/opt/maven/bin:/opt/nodejs/bin:${PATH}"

RUN node install -g yarn

ENTRYPOINT java $JAVA_OPTS -jar /opt/jenkins.war

VOLUME /root
