package yoru.example.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import yoru.example.entity.User;
import yoru.example.mapper.UserMapper;
import yoru.example.service.UserService;

import java.util.Date;
import java.util.List;

/**
 * @author Yorushika
 * @date 2020/8/28-20:14
 **/
@Service
public class UserServiceImpl implements UserService {
    private static final int NAV_SIZE=5;
    @Autowired
    private UserMapper userMapper;
    @Override
    public void insertUser(User user) {
        user.setCreateTime(new Date());
        userMapper.insertUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    @Override
    public void deleteUserList(Integer[] ids) {
        userMapper.deleteUserList(ids);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public PageInfo<User> listUserByPage(int pageNo, int pageSize) {
        Page<User> page = PageHelper.startPage(pageNo, pageSize);
        List<User> userList = userMapper.listUser();
        return new PageInfo<User>(userList,NAV_SIZE);
    }

    @Override
    public User getUserByAccountAndPassword(User user) {
        return userMapper.getUserByAccountAndPassword(user);
    }

    @Override
    public User getUserByAccount(String account) {
        return userMapper.getUserByAccount(account);
    }

    @Override
    public PageInfo<User> listUserByKeyword(String keyword, int pageNo, int pageSize) {
        Page<User> page = PageHelper.startPage(pageNo, pageSize);
        List<User> userList = userMapper.listUserByKeyword("%"+keyword+"%");
        return new PageInfo<User>(userList,NAV_SIZE);
    }

    @Override
    public void insertUserRoles(int uid, int[] rids) {
        userMapper.insertUserRoles(uid,rids);
    }

    @Override
    public void deleteUserRoles(int uid, int[] rids) {
        userMapper.deleteUserRoles(uid,rids);
    }


}
