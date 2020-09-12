package yoru.example.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import yoru.example.entity.Permission;
import yoru.example.mapper.PermissionMapper;
import yoru.example.service.PermissionService;

import java.util.List;

/**
 * @author Yorushika
 * @date 2020/8/28-17:16
 **/
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public void insertPermission(Permission permission) {
        permissionMapper.insertPermission(permission);
    }

    @Override
    public void deletePermission(Integer id) {
        permissionMapper.deletePermission(id);
    }

    @Override
    public void updatePermission(Permission permission) {
        permissionMapper.updatePermission(permission);
    }

    @Override
    public Permission getPermissionById(Integer id) {
        return permissionMapper.getPermissionById(id);
    }

    @Override
    public List<Permission> listPermission() {
        return permissionMapper.listPermission();
    }

    @Override
    public List<Permission> listPermissionByRid(int rid) {
        return permissionMapper.listPermissionByRid(rid);
    }
}
