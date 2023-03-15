package com.dongdongshop.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.dongdongshop.data.Result;
import com.dongdongshop.data.UserVo;
import com.dongdongshop.dto.LoginUserDTO;
import com.dongdongshop.filter.LoginUserServiceApi;
import com.dongdongshop.filter.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("userWeibo")
@CrossOrigin
public class UserWeiboLogin {

    @Value("${login.CODEURL}")
    private String CODEURL;

    @Value("${login.CLIENT_ID}")
    private String CLIENT_ID;

    @Value("${login.REDIRECT_URI}")
    private String REDIRECT_URI;

    @Value("${login.TOKENURL}")
    private String TOKENURL;

    @Value("${login.CLIENT_SECRET}")
    private String CLIENT_SECRET;

    @Value("${login.USERINFOURL}")
    private String USERINFOURL;

    @Autowired
    private LoginUserServiceApi serviceApi;

    @RequestMapping("toLogin")
    public String getGrantUrl(){
        //获取授权界面地址
        String codeUrl = CODEURL.replace("CLIENT_ID", CLIENT_ID)
                .replace("REDIRECT_URI", REDIRECT_URI);
        return "redirect:"+codeUrl;
    }

    @RequestMapping("callback")
    public String callback(String code, Model model) throws Exception {
        System.out.println("获取的code:" + code);
        Map<String, Object> map = new HashMap<>();
        map.put("client_id", CLIENT_ID);
        map.put("client_secret", CLIENT_SECRET);
        map.put("grant_type", "authorization_code");
        map.put("redirect_uri", REDIRECT_URI);
        map.put("code",code);
        //使用HttpClientUtil工具类发送请求,返回Json字符串
        String tokenJsonStr = HttpUtil.post(TOKENURL, map);
        //把Json字符串转换为json对象
        JSONObject jsonObject = JSONObject.parseObject(tokenJsonStr);
        //获取用户信息
        String accessToken = jsonObject.getString("access_token");
        String uid = jsonObject.getString("uid");

        Map<String, Object> map1 = new HashMap<>();
        map1.put("access_token", accessToken);
        map1.put("uid", uid);
        String tokenJsonStr1 = HttpUtil.get(USERINFOURL, map1);
        //把Json字符串转换为json对象
        JSONObject jsonObject1 = JSONObject.parseObject(tokenJsonStr1);
        //获取昵称
        String username = jsonObject1.getString("name");

//        Map<String, Object> map4 = new HashMap<>();
//        map.put("username",username);
//        String post = HttpUtil.post("http://7dae8a1.r9.cpolar.top/user/getUserByName", map);
//        Result<LoginUserDTO> object = JSONObject.parseObject(post, Result.class);
//        LoginUserDTO data = object.getData();
        Result<LoginUserDTO> result = null;
        try {
            result = serviceApi.getUserByName(username);
        }catch (Exception e){
            e.printStackTrace();
        }
        LoginUserDTO data = result.getData();
        if(data == null){
            data = new LoginUserDTO();
            Map<String, Object> map2 = new HashMap<>();
            map2.put("username",username);
            map2.put("password","qwqwqw");
            HttpUtil.post("http://7dae8a1.r9.cpolar.top/user/weiboLogin", map2);
            data.setUsername(username);
            data.setPassword("qwqwqw");
        }
        Map<String, Object> map3 = new HashMap<>();
        map3.put("username",username);
        map3.put("password","qwqwqw");
        String s = HttpUtil.post("http://7dae8a1.r9.cpolar.top/userLogin", map3);
        Result parseObject = JSONObject.parseObject(s, Result.class);
        String token = (String)parseObject.getData();
        System.out.println("token为 :  " + token);
        return "redirect:http://4af183c6.r9.cpolar.top/shop/index.html?token=" + token;
    }

   /* private String getUserInfo(String accessToken) throws Exception {
        //获取用户信息的接口
//        String userInfoUrl = USERINFOURL
//                .replace("ACCESS_TOKEN", accessToken);
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", accessToken);
        String tokenJsonStr = HttpUtil.get(USERINFOURL, map);
        //把Json字符串转换为json对象
        JSONObject jsonObject = JSONObject.parseObject(tokenJsonStr);
        //获取昵称
        String name = jsonObject.getString("name");
        System.out.println(name);
        return name;
    }*/

}
