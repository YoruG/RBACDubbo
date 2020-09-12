package yoru.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import yoru.example.entity.Permission;
import yoru.example.entity.ResultMsg;
import yoru.example.service.PermissionService;
import yoru.example.shiro.ShiroService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yorushika
 * @date 2020/8/28-17:29
 **/
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Reference(timeout = 3500)
    private PermissionService permissionService;
    @Resource
    private ShiroService shiroService;

    @GetMapping("/list")
    public ResultMsg<List<Permission>> list(){
        List<Permission> permissions = permissionService.listPermission();
        return new ResultMsg<>(200,"SUCCESS",permissions);
    }



    @PostMapping("/permission")
    public ResultMsg<String> addPermission(Permission permission){
        try{
            permissionService.insertPermission(permission);
            shiroService.updatePermission();
            return new ResultMsg<>(200,"SUCCESS","添加权限成功");
        }
        catch(Exception e) {
            return new ResultMsg<>(500,"FAIL","添加权限失败");
        }
    }



    @GetMapping("/{id}")
    public ResultMsg<Permission> getPermission(@PathVariable(value = "id",required = false)Integer id)
    {
        Permission permission=permissionService.getPermissionById(id);
        return new ResultMsg<>(200,"SUCCESS", permission);
    }

    @PutMapping("/{id}")
    public ResultMsg<String> updatePermission(Permission permission){
        try{
            permissionService.updatePermission(permission);
            shiroService.updatePermission();
            return new ResultMsg<>(200,"SUCCESS", "更改权限信息成功");
        }
        catch(Exception e) {
            return new ResultMsg<>(500,"FAIL","更改权限信息失败");
        }
    }


    @DeleteMapping("/{id}")
    public ResultMsg<String> deletePermission(@PathVariable("id")int id){
        try{
            permissionService.deletePermission(id);
            shiroService.updatePermission();
            return new ResultMsg<>(200,"SUCCESS", "删除权限成功");
        }
        catch(Exception e) {
            return new ResultMsg<>(500,"FAIL","删除权限失败");
        }
    }

}
