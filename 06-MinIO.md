# MinIO

## 1、安装

```bash
# 拉取镜像
docker pull quay.io/minio/minio

# 创建数据存储目录
mkdir -p ~/minio/data

# 创建minio
docker run -d --name minio \
-p 9001:9000 -p 9090:9090 \
-v ~/minio/data:/data \
-e "MINIO_ROOT_USER=admin" \
-e "MINIO_ROOT_PASSWORD=admin123456" \
--restart=always \
quay.io/minio/minio server /data --console-address ":9090"
```



## 2、登录访问

[http://192.168.56.20:9090/](http://192.168.56.20:9090/)

- 账号：admin
- 密码：admin123456



## 3、依赖

```xml
<!-- common-util模块中加入如下依赖 -->
<dependency>
    <groupId>io.minio</groupId>
    <artifactId>minio</artifactId>
    <version>8.5.2</version>
</dependency>
```



## 4、测试

```java
// 创建一个Minio的客户端对象
MinioClient minioClient = MinioClient.builder()
    .endpoint("http://192.168.56.20:9001")
    .credentials("minioadmin", "minioadmin")
    .build();

boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("spzx-bucket").build());

// 如果不存在，那么此时就创建一个新的桶
if (!found) {
    minioClient.makeBucket(MakeBucketArgs.builder().bucket("spzx-bucket").build());
} else {  // 如果存在打印信息
    System.out.println("Bucket 'spzx-bucket' already exists.");
}

FileInputStream fis = new FileInputStream("D://01.jpg") ;
PutObjectArgs putObjectArgs = PutObjectArgs.builder()
    .bucket("spzx-bucket")
    .stream(fis, fis.available(), -1)
    .object("01.jpg")
    .build();
minioClient.putObject(putObjectArgs) ;

// 构建fileUrl
String fileUrl = "http://127.0.0.1:9000/spzx-bucket/01.jpg" ;
System.out.println(fileUrl);
```

