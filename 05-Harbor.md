# Harbor

## 1、安装

```bash
# 解压tgz包
tar -zxvf harbor-offline-installer-v2.8.2.tgz

cd /oharbor
cp harbor.yml.tmpl harbor.yml

# 修改配置文件        
vim harbor.yml           
# hostname修改为自己虚拟机的ip地址
# 注释所有的https的配置

# 进入到Harbor的解压目录，执行安装脚本
sh install.sh

# 启动 Harbor
docker compose -f harbor.yml up -d
# 关闭 Harbor
docker compose -f harbor.yml stop

# 编辑/etc/docker/daemon.json文件
vim /etc/docker/daemon.json

# 添加安全访问权限
{
  "insecure-registries":["http://192.168.56.20"],
  "registry-mirrors": ["https://5dfryjrh.mirror.aliyuncs.com"]
}
# 重启Docker
systemctl restart docker
```



## 2、登录访问

[http://192.168.56.20/](http://192.168.56.20/)

- 账号：admin
- 密码：Harbor12345



## 3、推送拉取镜像

```bash
# 登录到Harbor
docker login -u admin -p Harbor12345 192.168.56.20

# 给镜像重新打一个标签
docker tag ebuy-docker 192.168.56.20/ebuy-docker/ebuy-docker:latest

# 推送镜像
docker push 192.168.56.20/ebuy-docker/ebuy-docker:latest

# 拉取镜像
docker pull 192.168.56.20/ebuy-docker/ebuy-docker:latest
```





