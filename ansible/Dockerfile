FROM pi4k8s/centos:7

RUN yum install -y wget \
    && wget -O /etc/yum.repos.d/epel.repo http://mirrors.aliyun.com/repo/epel-7.repo \
    && yum install -y ansible \
    && yum clean all && rm -rf /var/cache/yum/*