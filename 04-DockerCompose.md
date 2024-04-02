# Docker Compose

## 1、下载安装

```bash
# 创建指定目录存储docker compose
mkdir -p /usr/local/lib/docker/cli-plugins

# 安装方式1-联网下载：下载docker-compose并移动到上面的目录下： 下载可能会失败！！！！！！
curl -SL https://github.com/docker/compose/releases/download/v2.14.2/docker-compose-linux-x86_64 \
-o /usr/local/lib/docker/cli-plugins/docker-compose

# 安装方式2-通过xshell将课件`资料/day01`目录下的docker-compose 上传到上面的目录下：
# 给docker-compose文件赋予可执行权限
chmod +x /usr/local/lib/docker/cli-plugins/docker-compose

# 查看docker compose的版本
docker compose version

# 编辑 docker-compose.yml
cd /opt
vi docker-compose.yml
```

`docker-compose.yml`

```yaml
services:
  redis:
    image: redis:latest
    container_name: myredis
    ports:
      - "7379:6379"
    volumes:
      - redis-data:/data
volumes:
  redis-data: {}
```



## 2、相关命令

```bash
# 启动容器(如果不存在容器就创建、存在则修改)
docker compose -f docker-compose.yml up -d

# 停止所有容器
docker compose -f docker-compose.yml stop

# 启动所有容器
docker compose -f docker-compose.yml start

# 重启所有容器
docker compose -f docker-compose.yml restart

# 删除所有容器
docker compose -f docker-compose.yml down
```
