package yoru.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author Yorushika
 * @date 2020/8/28-16:21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String name;
    private String account;
    private String password;
    private String email;
    private Date createTime;
    private Set<Role> roleSet;

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }
}