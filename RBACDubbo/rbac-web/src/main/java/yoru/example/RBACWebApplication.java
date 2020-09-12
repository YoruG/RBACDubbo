package yoru.example;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Yorushika
 * @date 2020/8/28-16:29
 **/

@EnableDubbo
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RBACWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(RBACWebApplication.class,args);
    }
}
