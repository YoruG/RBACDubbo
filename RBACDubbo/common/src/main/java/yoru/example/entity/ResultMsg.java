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
@AllArgsConstructor
@NoArgsConstructor
public class ResultMsg<T> implements Serializable {
        private Integer status;
        private String info;
        private T data;
}
