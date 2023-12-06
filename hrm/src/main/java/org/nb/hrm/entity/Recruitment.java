package org.nb.hrm.entity;

import lombok.Data;

//招聘
@Data
public class Recruitment {
    //id
    private Long id;

    //工资
    private double wage;

    //工作时长
    private int workingHour;

    //工作描述
    private String description;

    //工作地点
    private String address;
}
