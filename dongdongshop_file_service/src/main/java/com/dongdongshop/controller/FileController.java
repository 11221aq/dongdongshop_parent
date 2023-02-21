package com.dongdongshop.controller;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.dongdongshop.data.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("file")
@RefreshScope
public class FileController {

    @Value("${update.file.url}")
    private String url;

    @Value("${update.file.keyId}")
    private String keyId;

    @Value("${update.file.KeySecret}")
    private String KeySecret;

    @Value("${update.file.bucketName}")
    private String name;

    @Value("${update.file.imgurl}")
    private String imgurl;

    @PostMapping
    public Result upload(@RequestParam MultipartFile myFile) {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String http = "https://";
        String endpoint = url;//  oss-cn-beijing.aliyuncs.com
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = keyId;  //   LTAI5t86un9ynSUvHHrKny5J
        String accessKeySecret = KeySecret;// d5y8cheY4vTZwwyPwZXbxLcXqBZ9uv
        // 填写Bucket名称，例如examplebucket。  "dk2207zs";
        String bucketName = name;
        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
        String objectName = imgurl +myFile.getOriginalFilename();  //   image/dongdongshop/

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(http+endpoint, accessKeyId, accessKeySecret);

        try {
            ossClient.putObject(bucketName, objectName, myFile.getInputStream());
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
//		https://dk2207zqf.oss-cn-beijing.aliyuncs.com/image/dongdongshop/5.jpg
        String url = http + bucketName + "." + endpoint+ "/" + objectName;
        return Result.ok().setData(url);
    }

}
