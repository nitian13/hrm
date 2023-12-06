package org.nb.hrm.entity;

import lombok.Data;

import java.io.Serializable;

//用户
@Data
public class User implements Serializable {
    //id
    private Long id;

    //手机号
    private String phone;

    //角色，普通用户0、公司1
    private int role;

    //token
    private String token;
}
