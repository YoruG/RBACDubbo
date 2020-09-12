package yoru.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yoru.example.entity.ResultMsg;
import yoru.example.entity.User;
import yoru.example.service.UserService;
import yoru.example.util.PasswordGenerateUtil;

import javax.servlet.http.HttpSession;

/**
 * @author Yorushika
 * @date 2020/8/28-22:11
 **/
@RestController
public class LoginController {

    @Reference(timeout = 3500)
    private UserService userService;
    @RequestMapping("logout")
    public String unAuthorized(HttpSession session){
        session.invalidate();
        return "login";
    }


    @PostMapping("doRegister")
    public ResultMsg<String> doRegister(User user){
        if (StringUtils.isEmpty(user.getAccount())||StringUtils.isEmpty(user.getPassword()))
            return new ResultMsg<>(400,"FAIL","注册信息未完善");
        try{
            user.setName(user.getAccount());
            user.setPassword(PasswordGenerateUtil.getMD5Password(user.getAccount(),user.getPassword(),1024));
            userService.insertUser(user);
            return new ResultMsg<>(200,"SUCCESS","注册用户成功");
        }
        catch(Exception e) {
            return new ResultMsg<>(500,"FAIL","服务器出现了一点儿问题");
        }
    }


    @PostMapping("doLogin")
    public ResultMsg<String> doLogin(User user){
      Subject currentUser= SecurityUtils.getSubject();
        try{
            String md5password=PasswordGenerateUtil.getMD5Password(user.getAccount(),user.getPassword(),1024);
            UsernamePasswordToken token=new UsernamePasswordToken(user.getAccount(),md5password);
            currentUser.login(token);
            return new ResultMsg<>(200,"SUCCESS","登录成功");
        }
        catch (UnknownAccountException e)
        {
            return new ResultMsg<>(500,"FAIL","账号不存在");
        }
        catch(AuthenticationException e)
        {
            return new ResultMsg<>(500,"FAIL","密码不正确");
        }
        catch(Exception e) {
            return new ResultMsg<>(500,"FAIL","服务器出现了一点儿问题");
        }
    }
}