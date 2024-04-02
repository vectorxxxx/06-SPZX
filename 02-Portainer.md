# Portainer

## 1、安装启动

```bash
# 搜索portainer
docker search portainer

# 拉取镜像
docker pull portainer/portainer

# 启动容器
# 注意需要做一个docker.sock文件的映射，后期portainer会通过这个文件和docker的守护进程进行通讯，管理docker的相关对象
# --restart=always: 表示随着docker服务的启动而启动
docker run -d -p 10010:9000 --name=portainer --restart=always \
-v /var/run/docker.sock:/var/run/docker.sock \
portainer/portainer
```



## 2、登录访问

[http://192.168.56.20:10010/](http://192.168.56.20:10010/)

- 账号：admin
- 密码：admin

![image-20240402112045222](https://s2.loli.net/2024/04/02/pYyeA9Dv7rNcIJ1.png)
