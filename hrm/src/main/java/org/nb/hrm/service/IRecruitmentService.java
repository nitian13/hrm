package org.nb.hrm.service;


import org.nb.hrm.entity.Recruitment;

import java.util.List;

public interface IRecruitmentService {
    int add(double wage,int workingHour,String description,String address);
    List<Recruitment> findJobByMoney(double wage);
    Recruitment findById(Long id);
    List<Recruitment> getRecruitmentList();
}
