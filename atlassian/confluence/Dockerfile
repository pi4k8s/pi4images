ARG version=8u201-jdk-ubuntu-18-04
ARG baseOracleJdk=pi4k8s/oraclejdk

FROM $baseOracleJdk:$version

ARG CONFLUENCE_VERSION=7.3.4

ENV CONFLUENCE_VERSION=$CONFLUENCE_VERSION

ENV JVM_MINIMUM_MEMORY=1024m

ENV JVM_MAXIMUM_MEMORY=1560m

RUN  cd /opt && apt update && apt install -y git wget \
     # 安装中文字体，解决宏名称乱码问题
     && apt install -y --no-install-recommends fonts-wqy-microhei fonts-wqy-zenhei \
     && wget -q "https://github.com/fastjrun/soft/releases/download/atlassian-confluence-$CONFLUENCE_VERSION/atlassian-confluence-$CONFLUENCE_VERSION.tar.gz" \
     && tar -zxf atlassian-confluence-$CONFLUENCE_VERSION.tar.gz \
     && rm -rf atlassian-confluence-$CONFLUENCE_VERSION.tar.gz \
     && apt remove -y --purge wget && apt clean \
     && rm -rf /tmp/* /var/cache/* /usr/share/doc/* /usr/share/man/* /var/lib/apt/lists/* \
     && sed -i '/\/var\/data\/confluence/s/# //g' /opt/atlassian-confluence-$CONFLUENCE_VERSION/confluence/WEB-INF/classes/confluence-init.properties \
     && sed -i "s/ms1024m/ms\${JVM_MINIMUM_MEMORY}/g" /opt/atlassian-confluence-$CONFLUENCE_VERSION/bin/setenv.sh \
     && sed -i "s/mx1024m/ms\${JVM_MAXIMUM_MEMORY}/g" /opt/atlassian-confluence-$CONFLUENCE_VERSION/bin/setenv.sh

ADD https://github.com/fastjrun/soft/releases/download/mysql-connector-java-5.1.47/mysql-connector-java-5.1.47.jar /opt/atlassian-confluence-$CONFLUENCE_VERSION/confluence/WEB-INF/lib

ENTRYPOINT /opt/atlassian-confluence-$CONFLUENCE_VERSION/bin/start-confluence.sh -fg

VOLUME /var/data/confluence

# arm64v8
# docker build . -t pi4k8s/confluence-mysql:7.3.4

# x86
# docker build . -t fastjrun/confluence-mysql:7.3.4 --build-arg baseOracleJdk=fastjrun/oraclejdk