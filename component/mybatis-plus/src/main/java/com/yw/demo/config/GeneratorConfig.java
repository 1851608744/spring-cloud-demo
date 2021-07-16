package com.yw.demo.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * @author yangwei
 * @description mybatis-plus 代码生成demo
 * @data 2021/07/14
 **/
public class GeneratorConfig {

    private static String DataBaseName = "demo_user";
    private static String Url = "jdbc:mysql://localhost:3306/" + DataBaseName + "?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
    private static String DriverName = "com.mysql.cj.jdbc.Driver";
    private static String UserName = "root";
    private static String Password = "root";
    private static String ProjectPath = System.getProperty("user.dir") + "/";


    /**
     * 读取控制台内容
     * @param tip
     * @return
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入").append(tip).append(": ");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "!");
    }

    /**
     *
     */
    public static void generator() {
        //代码生成器
        AutoGenerator generator = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(ProjectPath + scanner("子模块") + "/src/main/java");
        gc.setAuthor("yw");
        gc.setOpen(false);
        //重新生成时是否覆盖
        gc.setFileOverride(false);
        //开启Swagger2模式
        gc.setSwagger2(true);
        //定义生成的实体类中日期类型
        gc.setDateType(DateType.ONLY_DATE);
        gc.setServiceName("%sService");
        generator.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setUrl(Url);
        dataSourceConfig.setDriverName(DriverName);
        dataSourceConfig.setUsername(UserName);
        dataSourceConfig.setPassword(Password);
        generator.setDataSource(dataSourceConfig);

        //包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.yw.demo");
        pc.setEntity("domain");
        pc.setMapper("mapper");
        pc.setXml("mapper.xml");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        generator.setPackageInfo(pc);

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        //数据库表映射到实体的命名策略
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        //lombok模型 @Accessors(chain = true) setter
        strategyConfig.setEntityLombokModel(true);
        //restful api 风格控制器
        strategyConfig.setRestControllerStyle(true);
        //url中驼峰转连字符
        strategyConfig.setControllerMappingHyphenStyle(true);
        //公共父类
        //strategyConfig.setSuperControllerClass("");
        //写于父类中的公共字段
        strategyConfig.setSuperEntityClass("id");
        strategyConfig.setInclude(scanner("表名，多个英文逗号分割").split(","));
        //生成实体时去掉表前缀
        //strategyConfig.setTablePrefix(pc.getModuleName() + "_");
        //开启实体类的注解
        //strategyConfig.setEntityTableFieldAnnotationEnable(true);
        generator.setStrategy(strategyConfig);

        //模板配置
        generator.setTemplateEngine(new FreemarkerTemplateEngine());

        generator.execute();
    }

    public static void main(String[] args) {
        generator();
    }

}
