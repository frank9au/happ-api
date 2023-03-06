package com.happy.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.happy.base.BasePO;

import java.io.File;
import java.util.ArrayList;

public class MybatisPlusGeneratorUtils {

    /**
     * 表前缀
     */
    private static final String TABLE_PREFIX = "sys_";

    private static final String PACKAGE_NAME = "com.happy.sys.user";

    private static final String[] TABLE_NAME ={"sys_user"};

    public static void main(String[] args) {
        generateByTables();
    }


    private static void generateByTables() {

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)
                .setSuperEntityClass(BasePO.class)
                .setSuperEntityColumns(new String[]{"create_date","update_date"})
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setEntityColumnConstant(false)
                .setEntityTableFieldAnnotationEnable(true)
                .setTablePrefix(TABLE_PREFIX)
                .setNaming(NamingStrategy.underline_to_camel)
//                .setLogicDeleteFieldName("del_flag")
                .setTableFillList(new ArrayList() {{
                    add(new TableFill("create_date", FieldFill.INSERT));
                    add(new TableFill("update_date", FieldFill.INSERT_UPDATE));
                }})
                .setInclude(MybatisPlusGeneratorUtils.TABLE_NAME);

        String srcPath = "src" + File.separator + "main" + File.separator + "java";
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor(System.getProperty("user.name"))
                .setOutputDir(srcPath)
                .setEnableCache(false)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setKotlin(false)
                .setDateType(DateType.ONLY_DATE)
                .setActiveRecord(false)
                .setFileOverride(true)
                .setEntityName("%sPO")
                .setControllerName("%sApi")
                .setServiceName("I%sService");

        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig)
                .setDataSource(getDevDb())
                .setStrategy(strategyConfig)
                .setPackageInfo(new PackageConfig()
                        .setParent(PACKAGE_NAME)
                        .setController("api")
                        .setService("service")
                        .setServiceImpl("service.impl")
                        .setEntity("dao.po")
                        .setMapper("dao.mapper")
                        .setXml("dao.mapper.xml")
                        .setModuleName("")
                ).execute();
    }

    private static DataSourceConfig getDevDb() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        String dbUrl = "jdbc:mysql://localhost:4306/happy?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&serverTimezone=Asia/Shanghai";
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("happy")
                .setPassword("Happy@2023").setDriverName("com.mysql.cj.jdbc.Driver");
        return dataSourceConfig;
    }
}
