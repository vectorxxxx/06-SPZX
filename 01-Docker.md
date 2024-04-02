## 1、备份与迁移

```bash
# 将redis01容器保存为一个镜像
docker commit redis01 myredis

# 将myredis镜像保存为一个tar文件
docker save -o myredis.tar myredis

# 删除之前的myredis镜像
docker rmi myredis

# 将myredis.tar恢复成一个镜像
docker load -i myredis.tar
```



## 2、数据卷

```bash
# 查看数据卷
docker volume ls

# 创建数据卷
docker volume create redis-data

# 查询数据卷详情
docker volume inspect redis-data

# 删除数据卷
docker volume rm redis-data

# 数据卷挂载
# 格式: -v 数据卷名称:容器目录
docker run -d --name redis02 -p 6380:6379 \
-v redis-data:/data redis:7.0.10
```



## 3、开启远程访问

```bash
# 查找 docker.service 文件
find / -name "docker.service"

# 修改 docker.service
vi /usr/lib/systemd/system/docker.service

# 找到ExecStart行，修改成如下内容
# ExecStart=/usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock
ExecStart=/usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock -H tcp://0.0.0.0:2375

# 重启守护进程
systemctl daemon-reload
# 重启docker
systemctl restart docker
# 查看 docker 日志
systemctl status docker -l
# 关闭防火墙
systemctl stop firewalld.service
```

验证

```bash
# 查看端口 2375 是否已经被监听
yum install -y net-tools
# -n 参数表示以数字形式显示地址和端口号，而不进行域名解析。
# -l 参数表示仅显示监听状态的端口。
# -p 参数显示与端口相关联的进程信息。
# -a 参数表示显示所有连接和监听端口。
netstat -nlp | grep 2375
netstat -antp | grep dockerd


# 直接curl看是否生效，测试通过localhost是否能使用Docker Engine API
curl http://127.0.0.1:2375/info
curl http://localhost:2375/version


# 查看远程 Docker 引擎的版本信息
# 通过指定 -H 参数和远程 Docker 引擎的地址，可以连接到远程引擎并获取其版本信息。
docker -H tcp://192.168.56.20:2375 version
```
