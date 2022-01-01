FROM pi4k8s/openjdk8:centos7

ARG JENKINS_VERSION=2.327

ENV JAVA_OPTS='-Xmx1024m -Xms1024m'

RUN  cd /opt && yum install -y curl git \
     && curl -O -L -s "https://get.jenkins.io/war/$JENKINS_VERSION/jenkins.war" \
     && yum clean all && rm -rf /var/cache/yum/*

ENTRYPOINT java $JAVA_OPTS -jar /opt/jenkins.war

VOLUME /root/.jenkins