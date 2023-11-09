FROM alpine:3.10.3
RUN apk add --no-cache curl
ARG HELM_VERSION=unknown
ARG ARCH=unknown
ENV HELM_VERSION=$HELM_VERSION
ENV HELM_ARCH=$ARCH
RUN cd /tmp/ && wget https://get.helm.sh/helm-${HELM_VERSION}-linux-${HELM_ARCH}.tar.gz && tar -zxf helm-v*-linux-${HELM_ARCH}.tar.gz && mv linux-${HELM_ARCH}/helm /usr/local/bin/helm && rm -rf /tmp/*.tar.gz /tmp/linux-${HELM_ARCH}
CMD ["helm"]