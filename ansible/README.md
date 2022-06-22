# ansible

- 基于pi4k8s/centos:7构建
- git
- python3-pip
- pip 分别安装 ansible-2.10.7，3.4.0和4.10.0
- ansible-galaxy 安装 community.kubernetes

### name:tag
- pi4k8s/ansible-centos7-amd64:2.10.7 == pi4k8s/ansible:centos7 
  - 已知缺陷：x86架构服务器docker部署的jenkins的pipeline不支持部署helm应用
- pi4k8s/ansible-centos7-amd64:3.4.0 
- pi4k8s/ansible-centos7-amd64:4.10.0 

