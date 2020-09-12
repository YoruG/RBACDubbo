package yoru.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Yorushika
 * @date 2020/8/28-21:20
 **/
@Configuration
public class MVCConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
        viewResolver.setPrefix("view/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //使用这个ViewController要注意不能用二级路径
        //登录页
        registry.addViewController("/login").setViewName("login");
        //注册页
        registry.addViewController("/register").setViewName("register");
        //无权限页
        registry.addViewController("/unAuthorized").setViewName("unAuthorized");
        //主页
        registry.addViewController("/main").setViewName("main");
        //通用页
        registry.addViewController("/common").setViewName("common");


        //用户主页
        registry.addViewController("/user_index").setViewName("user/index");
        //用户添加页
        registry.addViewController("/user_add").setViewName("user/add");
        //用户分配角色页
        registry.addViewController("/user_assignRole").setViewName("user/assignRole");
        //用户编辑页
        registry.addViewController("/user_edit").setViewName("user/edit");


        //角色主页
        registry.addViewController("/role_index").setViewName("role/index");
        //角色添加页
        registry.addViewController("/role_add").setViewName("role/add");
        //角色分配角色页
        registry.addViewController("/role_assignPermission").setViewName("role/assignPermission");
        //角色编辑页
        registry.addViewController("/role_edit").setViewName("role/edit");


        //权限主页
        registry.addViewController("/permission_index").setViewName("permission/index");
        //权限添加页
        registry.addViewController("/permission_add").setViewName("permission/add");
        //权限编辑页
        registry.addViewController("/permission_edit").setViewName("permission/edit");

    }
}