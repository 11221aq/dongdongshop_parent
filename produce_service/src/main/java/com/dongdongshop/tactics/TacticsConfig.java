package com.dongdongshop.tactics;

import com.dongdongshop.annotate.CodePart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class TacticsConfig{

    public static Map<String, CodeStrategyHandler> map = new HashMap<>();

    @Autowired
    List<CodeStrategyHandler> codeStrategyHandlerList;

    //项目一启动，自动执行此方法中的逻辑
    @PostConstruct
    private void initHandler() {
        for (CodeStrategyHandler codeStrategyHandler : codeStrategyHandlerList) {
            //反射
            Class<? extends CodeStrategyHandler> aClass = codeStrategyHandler.getClass();
            String partType = aClass.getAnnotation(CodePart.class).partType();
            map.put(partType, codeStrategyHandler);
        }
    }
}
