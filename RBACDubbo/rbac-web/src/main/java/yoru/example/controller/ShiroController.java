package yoru.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yoru.example.entity.Permission;
import yoru.example.entity.ResultMsg;
import yoru.example.entity.User;
import yoru.example.service.FrontService;
import yoru.example.shiro.ShiroService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * @author Yorushika
 * @date 2020/9/12-16:56
 **/
@RestController
@RequestMapping("/shiro")
public class ShiroController {
    @Resource
    private FrontService frontService;

    @GetMapping("/permission")
    public ResultMsg<Set<Permission>> getUserPermission(HttpSession session)
    {
        User loginUser = (User) session.getAttribute("loginUser");
        System.out.println(loginUser);
        Set<Permission> userPermissions = frontService.getUserPermission(loginUser.getId());
        return new ResultMsg<>(200,"SUCCESS",userPermissions);
    }
}
