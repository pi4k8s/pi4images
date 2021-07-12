FROM pi4k8s/ubuntu:18.04

RUN apt update \
    && apt install make wget curl mongodb redis -y \
    && sed -i 's/::1//g' /etc/redis/redis.conf \
    && curl -sL https://deb.nodesource.com/setup_8.x | bash - \
    && apt install nodejs -y \
    && npm config set registry https://registry.npm.taobao.org \
    && apt clean \
    && rm -rf /tmp/* /var/cache/* /usr/share/doc/* /usr/share/man/* /var/lib/apt/lists/*

ADD https://github.com/easy-mock/easy-mock/archive/refs/tags/v1.6.0.tar.gz /opt

RUN cd /opt && tar -zxf v1.6.0.tar.gz && rm -f v1.6.0.tar.gz \
    && cd /opt/easy-mock-1.6.0 \
    && sed -i 's/0.2.2/0.5.0/g' package.json \
    && npm i

WORKDIR /opt/easy-mock-1.6.0

ENTRYPOINT  service mongodb start && service redis-server start && npm run dev

EXPOSE  7300
VOLUME /var/lib/mongodb

# arm64
# docker build . -t pi4k8s/easy-mock-arm64:1.6.0 && docker push pi4k8s/easy-mock-arm64:1.6.0

# amd64
# docker build . -t pi4k8s/easy-mock-amd64:1.6.0 && docker push pi4k8s/easy-mock-amd64:1.6.0


# manifest
# docker manifest create pi4k8s/easy-mock:1.6.0 pi4k8s/easy-mock-arm64:1.6.0 pi4k8s/easy-mock-amd64:1.6.0
# docker manifest annotate pi4k8s/easy-mock:1.6.0 pi4k8s/easy-mock-arm64:1.6.0 --os linux --arch arm64
# docker manifest annotate pi4k8s/easy-mock:1.6.0 pi4k8s/easy-mock-amd64:1.6.0 --os linux --arch amd64
# docker manifest push pi4k8s/easy-mock:1.6.0
#