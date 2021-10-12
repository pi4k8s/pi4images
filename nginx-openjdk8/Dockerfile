FROM nginx

RUN apt update \
    && apt install -y apt-transport-https ca-certificates wget dirmngr gnupg software-properties-common \
    && wget -qO - https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public | apt-key add - \
    && add-apt-repository --yes https://adoptopenjdk.jfrog.io/adoptopenjdk/deb/ \
    && apt update \
    && apt install -y adoptopenjdk-8-hotspot \
    && apt clean \
    && rm -rf /tmp/* /var/cache/* /usr/share/doc/* /usr/share/man/* /var/lib/apt/lists/*

# arm64
# docker build . -t pi4k8s/nginx-openjdk8-arm64:1.0 && docker push pi4k8s/nginx-openjdk8-arm64:1.0

# amd64
# docker build . -t pi4k8s/nginx-openjdk8-amd64:1.0 && docker push pi4k8s/nginx-openjdk8-amd64:1.0


# manifest
# docker manifest create pi4k8s/nginx-openjdk8:1.0 pi4k8s/nginx-openjdk8-arm64:1.0 pi4k8s/nginx-openjdk8-amd64:1.0
# docker manifest annotate pi4k8s/nginx-openjdk8:1.0 pi4k8s/nginx-openjdk8-amd64:1.0 --os linux --arch amd64
# docker manifest annotate pi4k8s/nginx-openjdk8:1.0 pi4k8s/nginx-openjdk8-arm64:1.0 --os linux --arch arm64
# docker manifest push pi4k8s/nginx-openjdk8:1.0
#