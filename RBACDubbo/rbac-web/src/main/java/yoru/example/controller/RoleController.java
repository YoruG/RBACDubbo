package yoru.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import yoru.example.entity.Permission;
import yoru.example.entity.ResultMsg;
import yoru.example.entity.Role;
import yoru.example.service.PermissionService;
import yoru.example.service.RoleService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Yorushika
 * @date 2020/8/28-20:06
 **/
@RestController
@RequestMapping("/role")
public class RoleController {
    @Reference(timeout = 3500)
    private RoleService roleService;
    @Reference(timeout = 3500)
    private PermissionService permissionService;

    @GetMapping("/list")
    public ResultMsg<List<Role>> list(){
        List<Role> roleList = roleService.listRole();
        return new ResultMsg<>(200,"SUCCESS",roleList);
    }

    @GetMapping("/unAssignPermissionAndAssignPermission/{id}")
    public ResultMsg<Map> assignPermission(@PathVariable("id")Integer id){
        Map<String,List<Permission>> map=new HashMap<>();
        List<Permission>  assignPermissionList= permissionService.listPermissionByRid(id);
        List<Permission> unAssignPermissionList=permissionService.listPermission();
        Iterator<Permission> it= unAssignPermissionList.iterator();
        while(it.hasNext()) {
            Permission permission = it.next();
            for (Permission p:assignPermissionList)
                if (p.getId()==permission.getId())
                    it.remove();
        }
        map.put("assignPermissionList",assignPermissionList);
        map.put("unAssignPermissionList",unAssignPermissionList);
        return new ResultMsg<>(200,"SUCCESS",map);
    }

    @PostMapping("/insertRolePermission")
    public ResultMsg<String> insertRolePermission(@RequestParam("rid") int rid, @RequestParam("unAssignList") int[] unAssignList)
    {
        try{
            roleService.insertRolePermissions(rid,unAssignList);
            return new ResultMsg<>(200,"SUCCESS","角色分配权限成功");
        }
        catch(Exception e) {
            return new ResultMsg<>(500,"FAIL","角色分配权限失败");
        }
    }

    @PostMapping("/deleteRolePermission")
    public ResultMsg<String> deleteRolePermission(@RequestParam("rid") int rid, @RequestParam("assignList") int[] assignList)
    {
        try{
            roleService.deleteRolePermissions(rid,assignList);
            return new ResultMsg<>(200,"SUCCESS","角色移除权限成功");
        }
        catch(Exception e) {
            return new ResultMsg<>(500,"FAIL","角色移除权限失败");
        }
    }

    @PostMapping("/role")
    public ResultMsg<String> addRole(Role role){
        try{
            roleService.insertRole(role);
            return new ResultMsg<>(200,"SUCCESS","添加角色成功");
        }
        catch(Exception e) {
            return new ResultMsg<>(500,"FAIL","添加角色失败");
        }
    }


    @GetMapping("/{id}")
    public ResultMsg<Role> getRole(@PathVariable(value = "id",required = false)Integer id)
    {
        Role role=roleService.getRoleById(id);
        return new ResultMsg<>(200, "SUCCESS", role);
    }


    @PutMapping("/{id}")
    public ResultMsg<String> updateRole(Role role){
        try{
            roleService.updateRole(role);
            return new ResultMsg<>(200,"SUCCESS","更改角色信息成功");
        }
        catch(Exception e) {
            return new ResultMsg<>(500,"FAIL","更改角色信息失败");
        }
    }

    @DeleteMapping("/{id}")
    public ResultMsg<String> deleteRole(@PathVariable("id")int id){
        try{
            roleService.deleteRole(id);
            return new ResultMsg<>(200,"SUCCESS","删除角色成功");
        }
        catch(Exception e) {
            return new ResultMsg<>(500,"FAIL","删除角色失败");
        }
    }


}