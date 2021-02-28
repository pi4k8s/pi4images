ARG version=18.04
ARG baseUbuntu=arm64v8/ubuntu

FROM $baseUbuntu:$version

ENV TZ Asia/Shanghai

RUN  sed -i "s/ports.ubuntu.com/mirrors.aliyun.com/g" /etc/apt/sources.list \
     && apt update && apt upgrade -y \
     && apt -y install language-pack-zh-hans tzdata \
     && ln -snf /usr/share/zoneinfo/TZ /etc/localtime && echo TZ > /etc/timezone \
     && dpkg-reconfigure -f noninteractive tzdata \
     && apt clean \
     && rm -rf /tmp/* /var/cache/* /usr/share/doc/* /usr/share/man/* /var/lib/apt/lists/*

ENV LANG zh_CN.UTF-8
ENV LANGUAGE zh_CN:zh
ENV LC_ALL zh_CN.UTF-8

# arm64
# docker build . -t pi4k8s/ubuntu-arm64:18.04

# amd64
# docker build . -t pi4k8s/ubuntu-amd64:18.04 --build-arg baseUbuntu=ubuntu  --build-arg version=18.04

# manifest
# docker manifest create pi4k8s/ubuntu:18.04 pi4k8s/ubuntu-amd64:18.04 pi4k8s/ubuntu-arm64:18.04
# docker manifest annotate pi4k8s/ubuntu:18.04 pi4k8s/ubuntu-amd64:18.04 --os linux --arch amd64
# docker manifest annotate  pi4k8s/ubuntu:18.04 pi4k8s/ubuntu-arm64:18.0 --os linux --arch arm64
# docker manifest push pi4k8s/ubuntu:18.04
#