package org.nb.hrm.entity;

import lombok.Data;

//课程
@Data
public class Course {
    //id
    private Long id;

    //时长
    private String duration;

    //内容
    private String content;

    //名字
    private String name;

    //课程老师
    private String teacher;

}
