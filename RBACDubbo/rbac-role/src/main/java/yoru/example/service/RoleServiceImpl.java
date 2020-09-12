package yoru.example.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import yoru.example.entity.Role;
import yoru.example.mapper.RoleMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Yorushika
 * @date 2020/8/28-20:04
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public void insertRole(Role role) {
        roleMapper.insertRole(role);
    }
    @Override
    public void deleteRole(Integer id) {
        roleMapper.deleteRole(id);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateRole(role);
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleMapper.getRoleById(id);
    }

    @Override
    public List<Role> listRole() {
        return roleMapper.listRole();
    }

    @Override
    public List<Role> listRoleByIds(int[] ids) {
        return roleMapper.listRoleByIds(ids);
    }

    @Override
    public List<Role> listRoleByUid(int uid) {
        return roleMapper.listRoleByUid(uid);
    }

    @Override
    public void insertRolePermissions(int rid, int[] pids) {
        roleMapper.insertRolePermissions(rid,pids);
    }

    @Override
    public void deleteRolePermissions(int rid, int[] pids) {
        roleMapper.deleteRolePermissions(rid,pids);
    }

    @Override
    public Set<String> getRoleNamesByUid(int uid) {
        List<Role> roleList = roleMapper.listRoleByUid(uid);
        Set<String> roleNames=new HashSet<>();
        for (Role role:roleList) {
            roleNames.add(role.getName());
        }
        return roleNames;
    }
}