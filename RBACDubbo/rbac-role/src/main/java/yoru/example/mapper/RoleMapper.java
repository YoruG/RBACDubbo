package yoru.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yoru.example.entity.Role;

import java.util.List;

/**
 * @author Yorushika
 * @date 2020/8/28-20:04
 **/
@Mapper
public interface RoleMapper {
    void insertRole(Role role);
    void deleteRole(Integer id);
    void updateRole(Role role);
    Role getRoleById(Integer id);
    List<Role> listRole();
    List<Role> listRoleByUid(int uid);
    List<Role> listRoleByIds(@Param("ids") int[] ids);
    void insertRolePermissions(@Param("rid")int rid, @Param("pids")int[] pids);
    void deleteRolePermissions(@Param("rid")int rid,@Param("pids")int[] pids);
}
