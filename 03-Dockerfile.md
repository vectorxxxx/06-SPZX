# Dockerfile

**案例一**

```bash
# 创建目录
mkdir –p /opt/dockerfilejdk17
cd /opt/dockerfilejdk17

# 编辑 dockerfile
vi dockerfile
```

`dockerfile`

```dockerfile
FROM centos:7
MAINTAINER vectorx
RUN mkdir -p /usr/local/java
ADD jdk-17_linux-x64_bin.tar.gz /usr/local/java/
ENV JAVA_HOME=/usr/local/java/jdk-17.0.8
ENV PATH=$PATH:$JAVA_HOME/bin
```

构建镜像、创建容器

```bash
# 构建镜像
docker build -t centos7-jdk17 -f dockerfile .

# 创建容器
docker run -it --name vectorx-centos centos7-jdk17 /bin/bash
```



**案例二**

```bash
mkdir /opt/dockerfileebuy
cd /opt/dockerfileebuy
vi dockerfile
```

`dockerfile`

```bash
FROM centos7-jdk17
MAINTAINER vectorx

# 声明容器内主进程所对应的端口
EXPOSE 8081
ADD ebuy-docker-1.0-SNAPSHOT.jar /ebuy-docker-1.0-SNAPSHOT.jar

# 相当于windows中的cd命令
WORKDIR /      
ENTRYPOINT ["java" , "-jar" , "ebuy-docker-1.0-SNAPSHOT.jar"]
```

构建镜像、创建容器

```bash
docker build -t ebuy-docker:v1.0 .

docker run -d --name ebuy-docker -p 8081:8081 ebuy-docker:v1.0
```

