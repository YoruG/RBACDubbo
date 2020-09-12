package yoru.example.service;

import yoru.example.entity.Permission;

import java.util.List;

/**
 * @author Yorushika
 * @date 2020/8/28-17:17
 **/
public interface PermissionService {
    void insertPermission(Permission permission);
    void deletePermission(Integer id);
    void updatePermission(Permission permission);
    Permission getPermissionById(Integer id);
    List<Permission> listPermission();
    List<Permission> listPermissionByRid(int rid);
}
