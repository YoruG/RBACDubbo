package yoru.example.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import yoru.example.entity.Permission;
import yoru.example.entity.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Yorushika
 * @date 2020/9/12-17:21
 **/
@Service
public class FrontService {

    @Reference(timeout = 3500)
    private RoleService roleService;
    @Reference(timeout = 3500)
    private UserService userService;
    @Reference(timeout = 3500)
    private PermissionService permissionService;
    public Set<Permission> getUserPermission(int uid)
    {
        Set<Permission> set=new HashSet<>();
        List<Role> roleList = roleService.listRoleByUid(uid);
        System.out.println("当前用户拥有的角色如下");
        for(Role role:roleList)
        {
            System.out.print(role+":");
            List<Permission> permissionList = permissionService.listPermissionByRid(role.getId());
            for (Permission permission:permissionList)
            {
                set.add(permission);
                System.out.println(permission.getName());
            }
        }
        return set;
    }
}
