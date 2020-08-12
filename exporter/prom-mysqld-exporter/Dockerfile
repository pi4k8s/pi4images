FROM arm64v8/ubuntu:18.04

RUN  sed -i "s/ports.ubuntu.com/mirrors.aliyun.com/g" /etc/apt/sources.list \
     && cd opt && apt update && apt upgrade -y \
     && apt -y install wget \
     && wget -q "https://github.com/prometheus/mysqld_exporter/releases/download/v0.12.1/mysqld_exporter-0.12.1.linux-arm64.tar.gz" \
     && tar -zxf mysqld_exporter-0.12.1.linux-arm64.tar.gz \
     && rm -rf mysqld_exporter-0.12.1.linux-arm64.tar.gz \
     && apt remove -y --purge wget && apt clean \
     && rm -rf /tmp/* /var/cache/* /usr/share/doc/* /usr/share/man/* /var/lib/apt/lists/* \
     && mv /opt/mysqld_exporter-0.12.1.linux-arm64/mysqld_exporter /bin/mysqld_exporter

USER        nobody
EXPOSE      9104
ENTRYPOINT  [ "/bin/mysqld_exporter" ]

# arm64v8
# docker build . -t pi4k8s/prom-mysqld-exporter:v0.12.1