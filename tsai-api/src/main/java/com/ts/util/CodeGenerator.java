package com.ts.util;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码自动生成模块
 */
public class CodeGenerator {
    //模块名字
    public static final String moduleName="";
    //包配置
    public static final String parent="com.sz.web.mvc";
    //表名，多个英文逗号分割
    public static final String table="sz_detection_fall,sz_equipment_fall";
    //数据库地址
//    jdbc:mysql://192.168.1.35:3307/sz_healthy?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
    public static final String url="jdbc:mysql://192.168.1.35:3307/sz_healthy?useUnicode=true&characterEncoding=UTF-8&useSSL=false&useTimezone=true&serverTimezone=Asia/Shanghai&allowMultiQueries=true";
    //数据库用户名
    public static final String username="free";
    //数据库密码
    public static final String password="free";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        String projectPath = System.getProperty("user.dir")+"/sz-api";
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir( projectPath+ "/src/main/java");
        //主键自增
        gc.setIdType(IdType.AUTO);
        //开启AR模式（暂时不用）
        //gc.setActiveRecord(true);
        //去掉接口前面恶心的I
        gc.setServiceName("%sService");
        gc.setAuthor("xu");
        gc.setOpen(false);
        //xml的映射
        gc.setBaseResultMap(true);
        //sql查询片段
        gc.setBaseColumnList(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent(parent);
        pc.setEntity("domain");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(table.split(","));
        strategy.setControllerMappingHyphenStyle(false);
        strategy.setTablePrefix("sz_");
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        //自动填充字段
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategy.setTableFillList(tableFills);

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // xml 生成位置，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setServiceImpl("templates/serviceImpl.java");
        tc.setXml(null);
        mpg.setTemplate(tc);

        // 使用自定义配置（xml位置）
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.execute();
    }

}
