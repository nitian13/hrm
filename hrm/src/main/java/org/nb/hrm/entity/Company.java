package org.nb.hrm.entity;

import lombok.Data;

//公司
@Data
public class Company {
    //id
    private Long id;

    //公司名称
    private String name;

    //公司地址
    private String address;

    //公司注册时间
    private Long registerTime;

    //公司手机号
    private String phone;

    //类型，0公司、1培训机构
    private int type;


}
