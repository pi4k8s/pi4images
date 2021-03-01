ARG version=7
ARG baseCentos=arm64v8/centos

FROM $baseCentos:$version

RUN  yum install kde-l10n-Chinese glibc-common -y \
     && localedef -c -f UTF-8 -i zh_CN zh_CN.utf8 \
     && export LANG=zh_CN.UTF-8 && echo "export LANG=zh_CN.UTF-8" >> /etc/locale.conf \

ENV LANG zh_CN.UTF-8
ENV LANGUAGE zh_CN:zh
ENV LC_ALL zh_CN.UTF-8
ENV TZ Asia/Shanghai

# arm64
# docker build . -t pi4k8s/centos-arm64:7

# amd64
# docker build . -t pi4k8s/centos-amd64:7 --build-arg baseCentos=centos  --build-arg version=7

# manifest
# docker manifest create pi4k8s/centos:7 pi4k8s/centos-amd64:7 pi4k8s/centos-arm64:7
# docker manifest annotate pi4k8s/centos:7 pi4k8s/centos-amd64:7 --os linux --arch amd64
# docker manifest annotate pi4k8s/centos:7 pi4k8s/centos-arm64:7 --os linux --arch arm64
# docker manifest push pi4k8s/centos:7