package proj.emcegom.quality.assess.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;
import java.util.Collections;

public class MyBatisCodeGenerator {

    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3307/demo?useSSL=false";

    public static final String USER_NAME = "root";
    public static final String PASSWORD = "123456";

    public static final String MAIN_JAVA_PATH = "/src/main/java";

    public static final String MAIN_MAPPER_PATH = "/src/main/resources/mapper";


    public static final String PACKAGE_NAME = "pers.emcegom.testing.platform";
    public static final String MODULE_NAME = "";

    public static final String TABLE_NAME = "demo_user";

    private static final String TABLE_PREFIX = "";

    public static void main(String[] args) {
        System.out.println(Paths.get(System.getProperty("user.dir")));
        FastAutoGenerator.create(JDBC_URL, USER_NAME, PASSWORD)
                .globalConfig(builder -> builder
                        .author("otis")
                        .outputDir(Paths.get(System.getProperty("user.dir")) + MAIN_JAVA_PATH)
                        .disableOpenDir()
                ).packageConfig(builder -> builder
                        .parent(PACKAGE_NAME)
                        .moduleName(MODULE_NAME)
                        .pathInfo(Collections.singletonMap(OutputFile.xml, Paths.get(System.getProperty("user.dir")) + MAIN_MAPPER_PATH))
                ).strategyConfig(builder -> builder
                        .addInclude(TABLE_NAME)
                        .addTablePrefix(TABLE_PREFIX)
                        .entityBuilder().enableFileOverride().enableTableFieldAnnotation().enableLombok().enableChainModel()
                        .serviceBuilder().enableFileOverride().formatServiceFileName("%sService")
                        .mapperBuilder().enableFileOverride()
                        .controllerBuilder().enableRestStyle().enableHyphenStyle().enableFileOverride()
                        .entityBuilder().enableLombok().enableFileOverride()
                ).templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }

}
