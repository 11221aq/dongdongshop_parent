package com.dongdongshop.log;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    //1.切点通知 2. 通知(前置通知,后置通知,异常通知,环绕通知,返回后通知)
    public Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.dongdongshop.service.*.*(..)) || @annotation(com.dongdongshop.log.DLog)")
    public void aoplog(){}

    //前置通知
    @Before("aoplog()")
    public void doBefore(JoinPoint joinPoint){
        //打印参数和返回值
        Object[] args = joinPoint.getArgs();//获取每一个被调用的service方法的参数
        Signature signature = joinPoint.getSignature();
        logger.info("正在执行的方法为 : {} , 参数为 : {}",signature, args);
        System.out.println("前置通知");
    }
    //后置通知
    @AfterReturning(pointcut = "aoplog()",returning = "result")
    public void doAfter(Object result) throws Throwable{
        logger.info("方法执行结束 , 参数为 : {}", JSONObject.toJSONString(result));
    }

}
