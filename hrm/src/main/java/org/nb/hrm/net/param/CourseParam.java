package org.nb.hrm.net.param;

import lombok.Data;


@Data
public class CourseParam {
    //时长
    private String duration;
    //内容
    private String content;
    //名字
    private String name;
    //课程老师
    private String teacher;
}
