package proj.emcegom.quality.assess;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Hello world!
 */

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("proj.emcegom.quality.assess.mapper")
public class QualityAssessApplication {
    public static void main(String[] args) {
        SpringApplication.run(QualityAssessApplication.class, args);
    }
}
