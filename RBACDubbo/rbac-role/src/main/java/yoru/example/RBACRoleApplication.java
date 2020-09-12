package yoru.example;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Yorushika
 * @date 2020/8/28-16:45
 **/
@EnableDubbo
@SpringBootApplication
public class RBACRoleApplication {
    public static void main(String[] args) {
        SpringApplication.run(RBACRoleApplication.class,args);
    }
}
