package yoru.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import yoru.example.entity.Permission;

import java.util.List;

/**
 * @author Yorushika
 * @date 2020/8/28-17:13
 **/
@Mapper
public interface PermissionMapper {
    void insertPermission(Permission permission);
    void deletePermission(Integer id);
    void updatePermission(Permission permission);
    Permission getPermissionById(Integer id);
    List<Permission> listPermission();
    List<Permission> listPermissionByRid(int rid);
}
