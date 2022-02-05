FROM pi4k8s/centos:7

RUN yum install -y java-1.8.0-openjdk java-1.8.0-openjdk-devel \
    && yum clean all && rm -rf /var/cache/yum/*

# arm64
# docker build . -t pi4k8s/openjdk8-arm64:centos7 && docker push pi4k8s/openjdk8-arm64:centos7

# amd64
# docker build . -t pi4k8s/openjdk8-amd64:centos7 && docker push pi4k8s/openjdk8-amd64:centos7


# manifest
# docker manifest create pi4k8s/openjdk8:centos7 pi4k8s/openjdk8-amd64:centos7 pi4k8s/openjdk8-arm64:centos7
# docker manifest annotate pi4k8s/openjdk8:centos7 pi4k8s/openjdk8-amd64:centos7 --os linux --arch amd64
# docker manifest annotate pi4k8s/openjdk8:centos7 pi4k8s/openjdk8-arm64:centos7 --os linux --arch arm64
# docker manifest push pi4k8s/openjdk8:centos7
#