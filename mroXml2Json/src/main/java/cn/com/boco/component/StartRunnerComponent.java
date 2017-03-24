package cn.com.boco.component;

import cn.com.boco.service.MroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by zhaogj on 16/02/2017.
 */
@Component
@Slf4j
@Order(value = 1)
public class StartRunnerComponent implements CommandLineRunner {

    @Autowired
    private MroService mro;

    @Override
    public void run(String... args) throws Exception {
        log.info("start StartRunnerComponent");
        mro.doJob();
        log.info("end StartRunnerComponent");
    }
}
