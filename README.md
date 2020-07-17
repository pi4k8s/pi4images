# pi4images


### confluence
- 基于版本7.3.4
- 集成mysql驱动
- 不支持协同编辑，初始化后需要从后台配置里关闭

#### 启动命令

```shell script
# 运行
docker run -itd -v /opt/server/confluence-home:/var/data/confluence --name="confluence" -p 8090:8090 --restart always --privileged=true -e TZ=Asia/Shanghai pi4k8s/confluence-mysql:7.3.4
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

