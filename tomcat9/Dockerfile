FROM pi4k8s/openjdk8:centos7

ARG TOMCAT_VERSION=9.0.56

ADD "https://archive.apache.org/dist/tomcat/tomcat-9/v$TOMCAT_VERSION/bin/apache-tomcat-$TOMCAT_VERSION.tar.gz" /opt

RUN cd /opt && tar -zxf apache-tomcat-$TOMCAT_VERSION.tar.gz \
    && mv apache-tomcat-9.0.56 tomcat9 \
    && rm -rf /opt/tomcat9/webapps/examples \
    && rm -rf /opt/apache-tomcat-$TOMCAT_VERSION.tar.gz \
    && rm -rf /tmp/* /var/cache/* /usr/share/doc/* /usr/share/man/* /var/lib/apt/lists/*
