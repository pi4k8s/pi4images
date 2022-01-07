FROM pi4k8s/nginx-openjdk8:1.0

COPY entrypoint.sh /opt/entrypoint.sh

RUN apt update \
    && apt install -y gcc make \
    && cd /opt && wget -q https://download.redis.io/releases/redis-5.0.14.tar.gz \
    && tar -zxf redis-5.0.14.tar.gz \
    && cd redis-5.0.14/src \
    && make install \
    && cd /opt && rm -rf redis* \
    && chmod a+x /opt/entrypoint.sh

ENTRYPOINT /opt/entrypoint.sh