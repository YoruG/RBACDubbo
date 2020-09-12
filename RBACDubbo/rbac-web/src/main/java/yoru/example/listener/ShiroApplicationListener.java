package yoru.example.listener;

import org.springframework.stereotype.Component;
import yoru.example.shiro.ShiroService;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Yorushika
 * @date 2020/9/11-23:31
 **/
@Component
public class ShiroApplicationListener implements ServletContextListener {
    @Resource
    private ShiroService shiroService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        shiroService.updatePermission();
    }
}
