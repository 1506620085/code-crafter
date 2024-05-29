package com.tailored.generator;

import com.tailored.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("Hangzzz");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果123：");
        doGenerator(mainTemplateConfig);
    }

    public static void doGenerator(Object model) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");
        File parentFile  = new File(projectPath).getParentFile();
        String inputPath = parentFile.getAbsolutePath() + File.separator + "code-crafter-demo-projects/acm-template";
        String outputPath = projectPath;
        StaticGenerator.copyFilesByRecursive(inputPath,outputPath);
        // 生成动态文件
        String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/com/crafter/acm/MainTemplate.java";
        DynamicGenerator.doGenerate(inputDynamicFilePath,outputDynamicFilePath,model);
    }
}
