package yoru.example.config;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.management.ManagementService;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yoru.example.shiro.ShiroService;
import yoru.example.shiro.UserRealm;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Yorushika
 * @date 2020/9/11-20:05
 **/
@Configuration
public class ShiroConfig {
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm=new UserRealm();
        userRealm.setCredentialsMatcher(credentialsMatcher());
        return new UserRealm();
    }
    @Bean
    public HashedCredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);
        return credentialsMatcher;
    }
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        securityManager.setCacheManager(ehCacheManager());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/main");
        bean.setUnauthorizedUrl("/unAuthorized");
        return bean;
    }

    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager ehCacheManager=new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:shiro-ehcache.xml");
        return ehCacheManager;
    }
}
