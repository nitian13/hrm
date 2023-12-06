package org.nb.hrm.net.param;

import lombok.Data;


@Data
public class RecruitmentParam {
    private Long id;

    //工资
    private double wage;
    //工作时长
    private int workingHour;
    //工作描述
    private String description;
}
