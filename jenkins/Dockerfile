FROM pi4k8s/openjdk8:centos7

ARG JENKINS_VERSION=2.327

ENV JAVA_OPTS='-Xmx1024m -Xms1024m'

RUN  cd /opt && yum install -y curl git make gcc+ gcc-c++ \
     && curl -O -L -s "https://get.jenkins.io/war/$JENKINS_VERSION/jenkins.war" \
     && curl -O -L -s "https://dlcdn.apache.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.tar.gz" \
     && curl -fsSL https://rpm.nodesource.com/setup_14.x | bash - \
     && yum install -y nodejs && npm install -g yarn \
     && tar -zxf apache-maven-3.8.4-bin.tar.gz && mv apache-maven-3.8.4 maven \
     && rm -f apache-maven-3.8.4-bin.tar.gz \
     && yum clean all && rm -rf /var/cache/yum/*

ENV PATH "/opt/maven/bin:/opt/nodejs/bin:${PATH}"

ENTRYPOINT java $JAVA_OPTS -jar /opt/jenkins.war

VOLUME /root
