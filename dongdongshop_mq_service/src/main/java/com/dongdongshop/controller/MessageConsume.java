package com.dongdongshop.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.tea.TeaException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RocketMQMessageListener(consumerGroup = "mygroup", topic = "phoneNum")
public class MessageConsume implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    private Client client;

    @Value("${dysms.signName}")
    private String signName;

    @Value("${dysms.templateCode}")
    private String templateCode;

    @Override
    public void onMessage(String s) {

    }
    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                MessageExt messageExt = list.get(0);
                String msg = new String(messageExt.getBody());//消息内容
                int times = messageExt.getReconsumeTimes();//重试次数
                String msgId = messageExt.getMsgId();//id
                String topic = messageExt.getTopic();//topic

                String s = RandomStringUtils.randomNumeric(4);
                JSONObject json = new JSONObject();
                json.put("sms_code",s);
                redisTemplate.boundValueOps(msg).set(s,80,TimeUnit.MINUTES);
                try {
                    if(!redisTemplate.hasKey(msgId)){
                        // 工程代码泄露可能会导致AccessKey泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html
                        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                                .setPhoneNumbers(msg)
                                .setSignName(signName)
                                .setTemplateCode(templateCode)
                                .setTemplateParam(json.toJSONString());
                        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
                        try {
                            // 复制代码运行请自行打印 API 的返回值
                            client.sendSmsWithOptions(sendSmsRequest, runtime);
                        } catch (TeaException error) {
                            // 如有需要，请打印 error
                            com.aliyun.teautil.Common.assertAsString(error.message);
                        } catch (Exception _error) {
                            TeaException error = new TeaException(_error.getMessage(), _error);
                            com.aliyun.teautil.Common.assertAsString(error.message);
                        }
                        redisTemplate.boundValueOps(msgId).set(topic);
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }catch (Exception e){
                    System.out.println(times);
                    if(times >= 1){

                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            }
        });
    }
}
