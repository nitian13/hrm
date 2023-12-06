package org.nb.hrm.service.impl;

import org.nb.hrm.entity.Recruitment;
import org.nb.hrm.mapper.CompanyMapper;
import org.nb.hrm.mapper.RecruitmentMapper;
import org.nb.hrm.service.IRecruitmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class RecruitmentService implements IRecruitmentService {
    private RecruitmentMapper recruitmentMapper;

    public RecruitmentService(RecruitmentMapper recruitmentMapper) {
        this.recruitmentMapper = recruitmentMapper;
    }

    @Override
    public int add(double wage, int workingHour, String description, String address) {
        return recruitmentMapper.add(wage,workingHour,description,address);
    }

    @Override
    public List<Recruitment> findJobByMoney(double wage) {
        return recruitmentMapper.findJobByMoney(wage);
    }

    @Override
    public Recruitment findById(Long id) {
        return recruitmentMapper.findById(id);
    }

    @Override
    public List<Recruitment> getRecruitmentList() {
        return recruitmentMapper.getRecruitmentList();
    }
}
