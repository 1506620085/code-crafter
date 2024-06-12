package com.crafter.generator;

import com.crafter.model.MainTemplateConfig;
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
        String inputRootPath = "D:\\a_Programming_Tools\\c_Code\\c_Back_End\\c_Study_Area\\code-crafter\\code-crafter-demo-projects\\acm-template";
        String outputRootPath = "D:\\a_Programming_Tools\\c_Code\\c_Back_End\\c_Study_Area\\code-crafter\\acm-template";

        String inputPath;
        String outputPath;

        inputPath = new File(inputRootPath, "src/com/crafter/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/com/crafter/acm/MainTemplate.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,model);

        inputPath = new File(inputRootPath, ".gitignore").getAbsolutePath();
        outputPath = new File(outputRootPath, ".gitignore").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);

        inputPath = new File(inputRootPath, "README.md").getAbsolutePath();
        outputPath = new File(outputRootPath, "README.md").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);

    }
}
