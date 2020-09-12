package yoru.example.service;

import org.apache.ibatis.annotations.Param;
import yoru.example.entity.Role;

import java.util.List;
import java.util.Set;

/**
 * @author Yorushika
 * @date 2020/8/28-17:17
 **/
public interface RoleService {
    void insertRole(Role role);
    void deleteRole(Integer id);
    void updateRole(Role role);
    Role getRoleById(Integer id);
    List<Role> listRole();
    List<Role> listRoleByIds(int[] ids);
    List<Role> listRoleByUid(int uid);
    void insertRolePermissions(@Param("rid")int rid, @Param("pids")int[] pids);
    void deleteRolePermissions(@Param("rid")int rid,@Param("pids")int[] pids);
    Set<String> getRoleNamesByUid(int uid);
}