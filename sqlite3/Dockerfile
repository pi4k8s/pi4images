ARG version=18.04
ARG baseUbuntu=pi4k8s/ubuntu
FROM $baseUbuntu:$version

RUN apt update && apt install -y sqlite3 && apt clean \
    && rm -rf /tmp/* /var/cache/* /usr/share/doc/* /usr/share/man/* /var/lib/apt/lists/*

# arm64v8
# docker build . -t pi4k8s/sqlite3:3.22.0-ubuntu-18-04

# x86
# docker build . -t fastjrun/sqlite3:3.22.0-ubuntu-18-04 --build-arg baseUbuntu=fastjrun/ubuntu  --build-arg version=18.04