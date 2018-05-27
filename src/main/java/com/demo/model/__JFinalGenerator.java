package com.demo.model;

import com.demo.Config;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;

import javax.sql.DataSource;

/**
 * Created by zhenyu on 2018/5/24.
 */
public class __JFinalGenerator {
    public static DataSource getDataSource() {
        PropKit.use("config.txt");
        DruidPlugin druidPlugin = Config.createDruidPlugin();
        druidPlugin.start();
        return druidPlugin.getDataSource();
    }

    public static void main(String[] args){
        String dbName="ip_pool";
        String modelPackageName = __JFinalGenerator.class.getPackage().getName();
        String baseModelPackageName = modelPackageName+".base";
        String baseModelOutputDir = PathKit.getWebRootPath() + "/src/main/java/" + baseModelPackageName.replace(".","/");
        String modelOutputDir = baseModelOutputDir + "/..";
        String dataDictionaryFileName = "DataDictionary.txt";
        String mappingKitFileName = "MappingKit";
        if (StrKit.notBlank(dbName)) {
            dbName = dbName.toLowerCase();
            dataDictionaryFileName = dbName + "_" + dataDictionaryFileName;
            mappingKitFileName = dbName + "_" + mappingKitFileName;
        }
        // 创建生成器
        Generator generator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
        generator.setDataDictionaryFileName("_" + dataDictionaryFileName);
        generator.setMappingKitClassName("_" + mappingKitFileName);
        // 设置是否在 Model 中生成 dao 对象
        generator.setGenerateDaoInModel(true);
        // 设置是否生成链式 setter 方法
        generator.setGenerateChainSetter(true);
        // 设置是否生成字典文件
        generator.setGenerateDataDictionary(true);
        // 生成
        generator.generate();
        System.out.print(generator.toString());
    }
}
