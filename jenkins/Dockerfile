FROM pi4k8s/openjdk8:centos7

ARG JENKINS_VERSION=2.263.4

ENV JAVA_OPTS='-Xmx1024m -Xms1024m'

RUN  cd /opt && yum install -y curl \
     && curl -O -L -s "http://mirrors.jenkins.io/war-stable/$JENKINS_VERSION/jenkins.war" \
    && yum clean all && rm -rf /var/cache/yum/*

ENTRYPOINT java $JAVA_OPTS -jar /opt/jenkins.war

VOLUME /root/.jenkins

# arm64
# docker build . -t pi4k8s/jenkins-arm64:openjdk8 && docker push pi4k8s/jenkins-arm64:openjdk8

# amd64
# docker build . -t pi4k8s/jenkins-amd64:openjdk8 && docker push pi4k8s/jenkins-amd64:openjdk8


# manifest
# docker manifest create pi4k8s/jenkins:openjdk8 pi4k8s/jenkins-amd64:openjdk8 pi4k8s/jenkins-arm64:openjdk8
# docker manifest annotate pi4k8s/jenkins:openjdk8 pi4k8s/jenkins-amd64:openjdk8 --os linux --arch amd64
# docker manifest annotate pi4k8s/jenkins:openjdk8 pi4k8s/jenkins-arm64:openjdk8 --os linux --arch arm64
# docker manifest push pi4k8s/jenkins:openjdk8