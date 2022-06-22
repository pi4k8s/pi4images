FROM pi4k8s/centos:7

ARG ANSIBLE_VERSION=4.10.0

RUN yum install -y python3-pip git libselinux-python \
    && python3 -m pip install pip==21.3.1 -i https://pypi.tuna.tsinghua.edu.cn/simple \
    && python3 -m pip install ansible==${ANSIBLE_VERSION} -i https://pypi.tuna.tsinghua.edu.cn/simple \
    && ansible-galaxy collection install community.kubernetes \
    && yum clean all && rm -rf /var/cache/yum/*