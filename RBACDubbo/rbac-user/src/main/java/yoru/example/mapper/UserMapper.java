package yoru.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yoru.example.entity.User;

import java.util.List;

/**
 * @author Yorushika
 * @date 2020/8/28-20:14
 **/
@Mapper
public interface UserMapper {
    void insertUser(User user);
    void deleteUser(Integer id);
    void updateUser(User user);
    void deleteUserList(Integer[] ids);
    User getUserById(Integer id);
    List<User> listUserByKeyword(String keyword);
    List<User> listUser();
    User getUserByAccountAndPassword(User user);
    User getUserByAccount(String account);
    void insertUserRoles(@Param("uid")int uid, @Param("rids")int[] rids);
    void deleteUserRoles(@Param("uid")int uid,@Param("rids")int[] rids);
}
