package xyz.funnyboy.spzx.controller;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;

/**
 * @author VectorX
 * @version V1.0
 * @description
 * @date 2024-04-02 12:01:01
 */
@RestController
public class FileUploadController
{

    private static final String ENDPOINT = "http://192.168.56.20:9001";

    @GetMapping("/upload")
    public String upload() throws Exception {
        // 创建一个Minio的客户端对象
        MinioClient minioClient = MinioClient
                .builder()
                .endpoint(ENDPOINT)
                .credentials("minioadmin", "minioadmin")
                .build();

        boolean found = minioClient.bucketExists(BucketExistsArgs
                .builder()
                .bucket("spzx-bucket")
                .build());

        // 如果不存在，那么此时就创建一个新的桶
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs
                    .builder()
                    .bucket("spzx-bucket")
                    .build());
        }
        else {  // 如果存在打印信息
            System.out.println("Bucket 'spzx-bucket' already exists.");
        }

        // FileInputStream fis = new FileInputStream("D:\\workspace-mine\\06-SPZX\\backup\\01.jpg");
        FileInputStream fis = new FileInputStream("/01.jpg");
        PutObjectArgs putObjectArgs = PutObjectArgs
                .builder()
                .bucket("spzx-bucket")
                .stream(fis, fis.available(), -1)
                .object("01.jpg")
                .build();
        minioClient.putObject(putObjectArgs);

        // 构建fileUrl
        return ENDPOINT + "/spzx-bucket/01.jpg";
    }
}
