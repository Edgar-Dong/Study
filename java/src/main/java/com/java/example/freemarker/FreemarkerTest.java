package com.java.example.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author:無忌
 * @date:2020/11/2
 * @description:Freemarker测试类
 */
public class FreemarkerTest {
    private static final String TEMPLATE_PATH = "/Users/dhw/workspace/codes/Study/java/src/main/resources/templates";
    private static final String DEST_PATH = "/Users/dhw/workspace/codes/Study/java/src/main/java/com/java/example/freemarker";

    public static void main(String[] args) {
        autoGenJAVATest();
    }

    private static void autoGenJAVATest() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("classPath", "com.java.example.freemarker");
        dataMap.put("className", "AutoGenHello");
        dataMap.put("helloWorld", "通过<代码自动生成程序> 演示 FreeMarker的HelloWorld！");

        Configuration configuration = null;
        BufferedWriter out = null;
        try {
            configuration = new Configuration();
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            Template template = configuration.getTemplate("hello.ftl");
            File destFile = new File(DEST_PATH + File.separator + "AutoGenHello.java");
            if (!destFile.exists()) {
                destFile.createNewFile();
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destFile)));
            template.process(dataMap, out);
            System.out.println("模板文件创建成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
