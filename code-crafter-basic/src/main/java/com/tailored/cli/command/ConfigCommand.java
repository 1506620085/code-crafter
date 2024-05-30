package com.tailored.cli.command;

import cn.hutool.core.util.ReflectUtil;
import com.tailored.model.MainTemplateConfig;
import picocli.CommandLine;

import java.lang.reflect.Field;

/**
 * config 子命令
 * config 是一个辅助命令，作用是输出允许用户传入的动态参数的信息（也就是本项目 MainTemplateConfig 类的字段信息）。
 */
@CommandLine.Command(name = "config", description = "查看参数信息", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable {


    @Override
    public void run() {
        // 实现 congif 命令的逻辑
        System.out.println("查看参数信息");
//        // 获取要打印属性信息的类
//        Class<?> myClass = MainTemplateConfig.class;
//        // 获取类的所有字段
//        Field[] fields = myClass.getDeclaredFields();
        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);
        // 遍历并打印每个字段的信息
        for (Field field : fields) {
            System.out.println("字段名称：" + field.getName());
            System.out.println("字段类型：" + field.getType());
//            System.out.println("Modifiers: " + java.lang.reflect.Modifier.toString(field.getModifiers()));
            System.out.println("---");
        }
    }
}
