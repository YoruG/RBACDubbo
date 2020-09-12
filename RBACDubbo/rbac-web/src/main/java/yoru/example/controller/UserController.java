package yoru.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import yoru.example.entity.ResultMsg;
import yoru.example.entity.Role;
import yoru.example.entity.User;
import yoru.example.service.RoleService;
import yoru.example.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Yorushika
 * @date 2020/8/28-20:12
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Reference(timeout = 3500)
    private UserService userService;
    @Reference(timeout = 3500)
    private RoleService roleService;
    private static final int PAGE_SIZE=5;


    @PostMapping("/user")
    public ResultMsg<String> addUser(User user){
        try{
            user.setPassword("123456");
            userService.insertUser(user);
            return new ResultMsg<>(200,"SUCCESS","添加用户成功");
        }
        catch(Exception e) {
            return new ResultMsg<>(500,"FAIL","添加用户失败");
        }
    }


    @DeleteMapping("/list")
    public ResultMsg<String> deleteUsers(@RequestParam(value = "ids",required = false) String ids){
        String[] strings = ids.split(",");
        Integer[] array=new Integer[strings.length];
        for (int i=0;i<strings.length;i++)
            array[i]=Integer.parseInt(strings[i]);
        try{
            userService.deleteUserList(array);
            return new ResultMsg<>(200,"SUCCESS","批量删除用户成功");
        }
        catch(Exception e) {
            return new ResultMsg<>(500,"FAIL","批量删除用户失败");
        }
    }

    @DeleteMapping("/{id}")
    public ResultMsg<String> deleteUser(@PathVariable("id")int id){
        try{
            userService.deleteUser(id);
            return new ResultMsg<>(200,"SUCCESS","删除用户成功");
        }
        catch(Exception e) {
            return new ResultMsg<>(500,"FAIL","删除用户失败");
        }
    }

    @PutMapping("/{id}")
    public ResultMsg<String> updateUser(User user){
        try{
            userService.updateUser(user);
            return new ResultMsg<>(200,"SUCCESS","更新用户信息成功");
        }
        catch(Exception e) {
            return new ResultMsg<>(500,"FAIL","更新用户信息失败");
        }
    }

    @GetMapping("/{id}")
    public ResultMsg<User> getUser(@PathVariable("id")Integer id)
    {
        User user = userService.getUserById(id);
        return new ResultMsg<>(200,"SUCCESS",user);
    }


    @GetMapping("/list")
    public ResultMsg<PageInfo<User>> list(@RequestParam(value = "pageNo",required = false,defaultValue = "1") int pageNo){
        PageInfo<User> pageInfo = userService.listUserByPage(pageNo, PAGE_SIZE);
        return new ResultMsg<>(200,"SUCCESS",pageInfo);
    }

    @GetMapping("/search")
    public ResultMsg<PageInfo<User>> searchUser(@RequestParam(value = "keyword",required = false,defaultValue = "") String keyword,
                                                @RequestParam(value = "pageNo",required = false,defaultValue = "1") int pageNo ){
        PageInfo<User> pageInfo = userService.listUserByKeyword(keyword,pageNo,PAGE_SIZE);
        return new ResultMsg<>(200,"SUCCESS",pageInfo);
    }

    @GetMapping("/unAssignRoleAndAssignRole/{id}")
    public ResultMsg<Map> assignRole(@PathVariable("id")Integer id){
        Map<String,List<Role>> map=new HashMap<>();
        List<Role> assignRoleList = roleService.listRoleByUid(id);
        List<Role> unAssignRoleList=roleService.listRole();
        Iterator<Role> it= unAssignRoleList.iterator();
        while(it.hasNext()) {
            Role role = it.next();
            for (Role r:assignRoleList)
                if (r.getId()==role.getId())
                    it.remove();
        }
        map.put("assignRoleList",assignRoleList);
        map.put("unAssignRoleList",unAssignRoleList);
        return new ResultMsg(200,"SUCCESS",map);
    }

    @PostMapping("/insertUserRole")
    public ResultMsg<String> insertUserRole(@RequestParam("uid") int uid, @RequestParam("unAssignList") int[] unAssignList)
    {
        try{
            userService.insertUserRoles(uid,unAssignList);
            return new ResultMsg<>(200,"SUCCESS","用户分配角色成功");
        }
        catch(Exception e) {
            return new ResultMsg<>(500,"FAIL","用户分配角色失败");
        }
    }


    @PostMapping("/deleteUserRole")
    public ResultMsg<String> deleteUserRole(@RequestParam("uid") int uid, @RequestParam("assignList") int[] assignList)
    {
        try{
            userService.deleteUserRoles(uid,assignList);
            return new ResultMsg<>(200,"SUCCESS","用户移除角色成功");
        }
        catch(Exception e) {
            return new ResultMsg<>(500,"FAIL","用户移除角色失败");
        }
    }


    @GetMapping("/loginUser")
    public ResultMsg<User> getLoginUser(HttpSession session)
    {
        User loginUser = (User) session.getAttribute("loginUser");
        return new ResultMsg<>(200,"SUCCESS",loginUser);
    }

}
