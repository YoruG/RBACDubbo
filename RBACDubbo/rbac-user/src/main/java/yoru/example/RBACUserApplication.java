package yoru.example;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Yorushika
 * @date 2020/8/28-16:44
 **/
@EnableDubbo
@SpringBootApplication
public class RBACUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(RBACUserApplication.class,args);
    }
}
