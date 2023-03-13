package com.dk.demo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestDemo {

    @Test
    public void test() {
//        - 1.创建一个HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
//                - 2.创建一个Get请求 ,在括号里写上要调用的第三方的接口

        HttpGet httpGet = new HttpGet("http://dongdongshop.gateway.api.com:7001/contentCategory?id=1");
//                - 3.使用HttpClient来执行Get请求, 获取response
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){ //4.判断响应状态码是不是200，获取响应数据实体对象
                String s = EntityUtils.toString(response.getEntity(), "UTF-8");//第一个参数请求体 ,第二个参数 编码格式
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //
//                - 5.处理response
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//                - 6.关闭HttpClient
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //post请求
    @Test
    public void test1() {
        //        - 1.创建一个HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
//                - 2.创建一个Get请求 ,在括号里写上要调用的第三方的接口
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "test");
        StringEntity stringEntity = new StringEntity(JSONObject.toJSONString(map), "UTF-8");
        stringEntity.setContentType("application/json");

        HttpPut httpPut = new HttpPut("http://dongdongshop.gateway.api.com:7001/contentCategory");
        httpPut.setEntity(stringEntity);
//                - 3.使用HttpClient来执行Get请求, 获取response
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpPut);
            if(response.getStatusLine().getStatusCode() == 200){ //4.判断响应状态码是不是200，获取响应数据实体对象
                String s = EntityUtils.toString(response.getEntity(), "UTF-8");//第一个参数请求体 ,第二个参数 编码格式
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //
//                - 5.处理response
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//                - 6.关闭HttpClient
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //hutool
    @Test
    public void test2() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        String s = HttpUtil.get("http://dongdongshop.gateway.api.com:7001/contentCategory",map);
        System.out.println(s);
    }

    @Test//restful换个方式 map只是拼接路径
    public void test3() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 2);
        map.put("name", "test");
        HttpRequest.put("http://dongdongshop.gateway.api.com:7001/contentCategory")
        .body(JSONObject.toJSONString(map))// HttpRequest 的body ,用来放入参数
        .execute().body();//执行完之后返回一个对象 这个body 是执行完之后的结果集
    }

    @Test//文件上传
    public void test4() {
        File file = new File("D:\\toImg\\img\\product\\small\\m34f047ea4d1d1e0b832aa8a5dd55716c4.jpg");
        HashMap<String, Object> paramMap = new HashMap<>();
        //文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
        paramMap.put("myFile", file);
        String result= HttpUtil.post("http://dongdongshop.gateway.api.com:7001/file", paramMap);
        System.out.println(result);
    }

    @Test
    public void test5() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("content", "东科创想");
        map.put("mobile","19819535338");
        map.put("LTAI5t8egQ8mrPYi5LLEcNY7","XtLOW5rpVbgwIGHEuCCh95sFVP2gUa");
        String s = HttpUtil.get("https://cxsmsjk.api.bdymkt.com/chuangxinsms/dxjk", map);
        System.out.println(s);
    }

}
