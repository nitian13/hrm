package org.nb.hrm.service.impl;

import org.nb.hrm.entity.Company;
import org.nb.hrm.mapper.CompanyMapper;
import org.nb.hrm.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class CompanyService implements ICompanyService {
    private CompanyMapper companyMapper;

    @Autowired
    public CompanyService(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }


    @Override
    public int register(String name, String address, Long registerTime, String phone, int type) {
        return companyMapper.register(name,address,registerTime,phone,type);
    }

    @Override
    public Company findCompanyById(Long id) {
        return companyMapper.findCompanyById(id);
    }
}
