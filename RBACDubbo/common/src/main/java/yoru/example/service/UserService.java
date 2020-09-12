package yoru.example.service;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import yoru.example.entity.User;

/**
 * @author Yorushika
 * @date 2020/8/28-17:17
 **/
public interface UserService {
    void insertUser(User user);
    void deleteUser(Integer id);
    void deleteUserList(Integer[] ids);
    User getUserById(Integer id);
    void updateUser(User user);
    PageInfo<User> listUserByPage(int pageNo, int pageSize);
    User getUserByAccountAndPassword(User user);
    User getUserByAccount(String account);
    PageInfo<User>  listUserByKeyword(String keyword,int pageNo,int pageSize);
    void insertUserRoles(@Param("uid")int uid, @Param("rids")int[] rids);
    void deleteUserRoles(@Param("uid")int uid,@Param("rids")int[] rids);
}
