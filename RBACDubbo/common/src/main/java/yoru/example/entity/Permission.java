package yoru.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Yorushika
 * @date 2020/8/28-16:22
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements Serializable {
    private Integer id;
    private String name;
    private String url;
}