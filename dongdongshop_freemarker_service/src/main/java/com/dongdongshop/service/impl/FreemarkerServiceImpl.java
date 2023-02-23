package com.dongdongshop.service.impl;

import com.dongdongshop.service.FreemarkerService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


@Service
public class FreemarkerServiceImpl implements FreemarkerService {

    @Autowired
    private FreeMarkerConfigurer freemarkerConfigurer;

    @Override
    public void creatPage(Long goodsId) {
        //    第一步：创建一个 Configuration 对象，直接 new 一个对象。构造方法的参数就是 freemarker的版本号。
        Configuration configuration = freemarkerConfigurer.getConfiguration();
        //    第四步：加载一个模板，创建一个模板对象。
        Writer writer =null;
        try {
            Template template = configuration.getTemplate("item.ftl");
            //    第五步：创建一个模板使用的数据集，可以是 pojo 也可以是 map。一般是 Map。
            Map map = new HashMap();
            //    第六步：创建一个 Writer 对象，一般创建一 FileWriter 对象，指定生成的文件名。
            writer = new FileWriter("E:\\ideaproject\\dongdongshop_html\\goods\\" + goodsId + ".html");
            //    第七步：调用模板对象的 process 方法输出文件。
            try {
                template.process(map, writer);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //    第八步：关闭流
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
