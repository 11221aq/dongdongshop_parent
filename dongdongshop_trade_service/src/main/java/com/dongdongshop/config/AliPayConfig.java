package com.dongdongshop.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliPayConfig {

    @Value("${aliPay.serverUrl}")
    private String serverUrl;

    @Value("${aliPay.appId}")
    private String appId;

    @Value("${aliPay.privateKey}")
    private String privateKey;

    @Value("${aliPay.format}")
    private String format;

    @Value("${aliPay.charset}")
    private String charset;

    @Value("${aliPay.alipayPublicKey}")
    private String alipayPublicKey;

    @Value("${aliPay.signType}")
    private String signType;

    @Bean
    public AlipayClient alipayClient() {
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl,appId,privateKey, format,charset,alipayPublicKey,signType);
        return alipayClient;
    }

}
