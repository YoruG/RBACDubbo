package yoru.example.shiro;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import sun.security.provider.MD5;
import yoru.example.entity.Permission;
import yoru.example.entity.Role;
import yoru.example.entity.User;
import yoru.example.service.FrontService;
import yoru.example.service.PermissionService;
import yoru.example.service.RoleService;
import yoru.example.service.UserService;
import yoru.example.util.PasswordGenerateUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yorushika
 * @date 2020/9/11-20:04
 **/
public class UserRealm extends AuthorizingRealm {
    @Resource
    private FrontService frontService;
    @Reference(timeout = 3500)
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Session session = SecurityUtils.getSubject().getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        for (Permission permission:frontService.getUserPermission(loginUser.getId()))
        {
            info.addStringPermission(permission.getName());
            System.out.println(permission.getName());
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Session session = SecurityUtils.getSubject().getSession();
        String account = (String) authenticationToken.getPrincipal();
        String password =new String((char[]) authenticationToken.getCredentials());
        User findUser1=userService.getUserByAccount(account);
        if (findUser1==null)
            throw new UnknownAccountException();
        User findUser2 = userService.getUserByAccountAndPassword(new User(account,password));
        if (findUser2==null)
            throw new AuthenticationException();
        else
            session.setAttribute("loginUser",findUser2);
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(findUser2,password,getName());
        return info;
    }
}
