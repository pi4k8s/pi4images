ARG version=8u201-jdk-ubuntu-18-04
ARG baseOracleJdk=pi4k8s/oraclejdk

FROM $baseOracleJdk:$version

ARG JIRA_VERSION=8.8.0

ENV JIRA_VERSION=$JIRA_VERSION

ENV JIRA_HOME=/opt/jira/data

ENV JVM_SUPPORT_RECOMMENDED_ARGS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:MaxRAMFraction=1 -XX:MaxRAM=1800M"

ENV JVM_MINIMUM_MEMORY=1024m

ENV JVM_MAXIMUM_MEMORY=1560m

RUN  cd /opt && apt update && apt install -y git wget \
     && wget -q "https://github.com/fastjrun/soft/releases/download/atlassian-jira-software-$JIRA_VERSION/atlassian-jira-software-$JIRA_VERSION.tar.gz" \
     && tar -zxf atlassian-jira-software-$JIRA_VERSION.tar.gz \
     && rm -rf atlassian-jira-software-$JIRA_VERSION.tar.gz \
     && apt remove -y --purge wget && apt clean \
     && rm -rf /tmp/* /var/cache/* /usr/share/doc/* /usr/share/man/* /var/lib/apt/lists/* \
     && sed -i 27,28d /opt/atlassian-jira-software-$JIRA_VERSION-standalone/bin/setenv.sh \
     && sed -i 14d /opt/atlassian-jira-software-$JIRA_VERSION-standalone/bin/setenv.sh

ADD https://github.com/fastjrun/soft/releases/download/mysql-connector-java-5.1.47/mysql-connector-java-5.1.47.jar /opt/atlassian-jira-software-$JIRA_VERSION-standalone/atlassian-jira/WEB-INF/lib

ENTRYPOINT /opt/c-jira-software-$JIRA_VERSION-standalone/bin/start-jira.sh -fg

VOLUME $JIRA_HOME

# arm64v8
# docker build . -t pi4k8s/jira-mysql:8.8.0

# x86
# docker build . -t fastjrun/jira-mysql:8.8.0 --build-arg baseOracleJdk=fastjrun/oraclejdk