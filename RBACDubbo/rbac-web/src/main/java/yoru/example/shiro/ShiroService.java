package yoru.example.shiro;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yoru.example.entity.Permission;
import yoru.example.entity.ResultMsg;
import yoru.example.entity.Role;
import yoru.example.service.PermissionService;
import yoru.example.service.RoleService;
import yoru.example.service.UserService;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Yorushika
 * @date 2020/9/11-21:57
 **/
@Service
public class ShiroService {
    @Autowired
    ShiroFilterFactoryBean shiroFilterFactoryBean;
    @Reference(timeout = 3500)
    private PermissionService permissionService;
    //加载权限过滤链
    public Map<String,String> loadFilterChainDefinition(){
        Map<String,String> map=new LinkedHashMap<>();
        List<Permission> permissionList = permissionService.listPermission();
        for (Permission permission:permissionList)
            map.put(permission.getUrl(),"perms["+permission.getName()+"]");
        map.put("/**","anon");
        return map;
    }


    public void updatePermission() {
        synchronized (shiroFilterFactoryBean) {
            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
            // 清空老的权限控制
            manager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinition());
            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            System.out.println("更新后的权限过滤链");
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
                System.out.println("url: "+url+" chainDefinition:"+chainDefinition);
            }

            System.out.println("更新权限成功！！");
        }
    }
}
