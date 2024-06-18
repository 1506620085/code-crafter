package com.crafter.maker.meta;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class MetaValidator {

    public static void doValidAndFill(Meta meta) {

        validAndFillMetaRoot(meta);

        validAndFillFileConfig(meta);

        validAndFillModelConfig(meta);
    }

    /**
     * modelConfig 校验和默认值
     *
     * @param meta
     */
    private static void validAndFillModelConfig(Meta meta) {
        Meta.ModelConfigDTO modelConfig = meta.getModelConfig();
        if (modelConfig == null) {
            return;
        }
        List<Meta.ModelConfigDTO.ModelsDTO> models = modelConfig.getModels();
        if (CollectionUtil.isNotEmpty(models)) {
            for (Meta.ModelConfigDTO.ModelsDTO model : models) {
                // 输出路径默认值
                String fieldName = model.getFieldName();
                if (StrUtil.isBlank(fieldName)) {
                    throw new MetaException("未填写 fieldName");
                }

                model.setType(StrUtil.blankToDefault(model.getType(), "String"));
            }
        }
    }

    /**
     * fileConfig 校验和默认值
     *
     * @param meta
     */
    private static void validAndFillFileConfig(Meta meta) {

        Meta.FileConfigDTO fileConfig = meta.getFileConfig();
        if (fileConfig == null) {
            throw new MetaException("fileConfig is not null");
        }
        String sourceRootPath = fileConfig.getSourceRootPath();
        if (StrUtil.isBlank(sourceRootPath)) {
            throw new MetaException("未填写 sourceRootPath");
        }
        // inputRootPath: .source + sourceRootPath 的最后一个层级路径
        String inputRootPath = fileConfig.getInputRootPath();
        String defaultInputRootPath = ".source" + File.separator + FileUtil.getLastPathEle(Paths.get(sourceRootPath)).getFileName().toString();
        fileConfig.setInputRootPath(StrUtil.blankToDefault(inputRootPath, defaultInputRootPath));
        // outputRootPath: 默认为当前路径下的 generated
        String outputRootPath = fileConfig.getOutputRootPath();
        String defaultOutputRootPath = "generated";
        fileConfig.setOutputRootPath(StrUtil.blankToDefault(outputRootPath, defaultOutputRootPath));
        String fileConfigType = fileConfig.getType();
        String defaultType = "dir";
        fileConfig.setType(StrUtil.blankToDefault(fileConfigType, defaultType));
        // fileInfo 默认值
        List<Meta.FileConfigDTO.FilesDTO> files = fileConfig.getFiles();
        if (CollectionUtil.isNotEmpty(files)) {
            for (Meta.FileConfigDTO.FilesDTO file : files) {
                // inputPath 必填
                String inputPath = file.getInputPath();
                if (StrUtil.isBlank(inputPath)) {
                    throw new MetaException("inputPath 不能为空");
                }
                // outputPath 默认等于 inputPath
                String outputPath = file.getOutputPath();
                file.setOutputPath(StrUtil.blankToDefault(outputPath, inputRootPath));
                // type 默认 inputPath 有文件后缀（如 .java）为 file，否则为 dir
                String type = file.getType();
                if (StrUtil.isBlank(type)) {
                    // 无文件后缀则为 dir（目录） 有文件后缀则为 file（文件）
                    file.setType(StrUtil.isBlank(FileUtil.getSuffix(inputPath)) ? "dir" : "file");
                }
                // generateType 如果文件结尾不为 Ftl，generateType 默认为 static，否则为 dynamic
                String generateType = file.getGenerateType();
                if (StrUtil.isBlank(generateType)) {
                    file.setGenerateType(inputPath.endsWith(".ftl") ? "dynamic" : "static");
                }
            }
        }
    }

    /**
     * 基础信息校验和默认值
     *
     * @param meta
     */
    private static void validAndFillMetaRoot(Meta meta) {

        String name = meta.getName();
        // 如果为空设置成本机用户名
        meta.setName(StrUtil.blankToDefault(name, "my-generator"));

        String description = meta.getDescription();
        meta.setDescription(StrUtil.blankToDefault(description, "我的模板代码生成器"));

        String basePackage = meta.getBasePackage();
        meta.setBasePackage(StrUtil.blankToDefault(basePackage, "com.username"));

        String version = meta.getVersion();
        meta.setVersion(StrUtil.blankToDefault(version, "1.0"));

        String author = meta.getAuthor();
        // 获取本机用户名
        String username = System.getProperty("user.name");
        meta.setAuthor(StrUtil.blankToDefault(author, username));

        String createTime = meta.getCreateTime();
        meta.setCreateTime(StrUtil.blankToDefault(createTime, DateUtil.now()));
    }
}
