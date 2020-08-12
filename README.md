# pi4images

### confluence
- 基于版本7.3.4
- 集成mysql驱动
- 不支持协同编辑，初始化后需要从后台配置里关闭

#### 启动命令

```shell script
# 运行
docker run -d --name="confluence" \
  -v /opt/server/confluence-home:/var/data/confluence  \
  -p 8090:8090 \
  --restart always --privileged=true \
  -e TZ=Asia/Shanghai \
  pi4k8s/confluence-mysql:7.3.4
# 删除
docker rm confluence
# 进入容器
docker exec -it confluence bash
```

#### 启停
```shell script
# 启动
docker start confluence
# 停止
docker stop confluence
# 重新启动
docker restart confluence
```

### prom-mysqld-exporter
- 基于版本v0.12.1

#### 启动命令

```shell script
# 运行
docker run -d --name="mysqld-exporter" \
  -p 9104:9104  \
  -e DATA_SOURCE_NAME="user:password@(217.0.01:3306)/database" \
  pi4k8s/prom-mysqld-exporter:v0.12.1
# 删除
docker rm mysqld-exporter
# 进入容器
docker exec -it mysqld-exporter bash
```

#### 启停
```shell script
# 启动
docker start mysqld-exporter
# 停止
docker stop mysqld-exporter
# 重新启动
docker restart mysqld-exporter
```


