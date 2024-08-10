package proj.emcegom.quality.assess;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import proj.emcegom.quality.assess.entity.DemoUser;
import proj.emcegom.quality.assess.service.DemoUserService;


import java.util.List;

@SpringBootTest
public class CommTest {
    @Resource
    DemoUserService demoUserService;

    @Test
    void contextLoads(){
        DemoUser jack = DemoUser.builder().age(19).name("Jack").build();
        boolean save = demoUserService.save(jack);
        System.out.println(save);

        Page<DemoUser> page = new Page<>(1, 10);
        IPage<DemoUser> pageRecords = demoUserService.page(page, new LambdaQueryWrapper<DemoUser>().ge(DemoUser::getAge, 10));
        List<DemoUser> records = pageRecords.getRecords();
        records.forEach(System.out::println);
    }


}
