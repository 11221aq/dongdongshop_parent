package com.dongdongshop.test;


import com.dongdongshop.pojo.User;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;

public class FreemarkerTest {
    public static void main(String[] args) throws Exception {
        //    第一步：创建一个 Configuration 对象，直接 new 一个对象。构造方法的参数就是 freemarker的版本号。
        Configuration configuration = new Configuration(Configuration.getVersion());
//    第二步：设置模板文件所在的路径。
        configuration.setDirectoryForTemplateLoading(new File("E:\\ideaproject\\dongdongshop_parent\\dongdongshop_freemarker_service\\src\\main\\resources"));
//    第三步：设置模板文件使用的字符集。一般就是 utf-8.
        configuration.setDefaultEncoding("utf-8");
//    第四步：加载一个模板，创建一个模板对象。
        Template template = configuration.getTemplate("freemarker/test.ftl");
//    第五步：创建一个模板使用的数据集，可以是 pojo 也可以是 map。一般是 Map。
        Map map = new HashMap();
        map.put("name","zhangsan");
        map.put("price",100d);
        User u1 = new User("zhangsan",12);
        User u2 = new User("lisi",34);
        User u3 = new User("wangwu",56);
        User u4 = new User("zhaoliu",78);
        List list = new ArrayList();
        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);
        map.put("arraylist",list);
        map.put("date",new Date());
        map.put("a",null);
//    第六步：创建一个 Writer 对象，一般创建一 FileWriter 对象，指定生成的文件名。
        Writer writer = new FileWriter("E:\\ideaproject\\dongdongshop_html\\goods\\aaa.html");
//    第七步：调用模板对象的 process 方法输出文件。
        template.process(map, writer);
//    第八步：关闭流
        writer.close();
    }

}
