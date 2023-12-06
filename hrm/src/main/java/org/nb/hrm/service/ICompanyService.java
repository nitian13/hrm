package org.nb.hrm.service;

import org.nb.hrm.entity.Company;


public interface ICompanyService {
    int register(String name,String address,Long registerTime,String phone,int type);
    Company findCompanyById(Long id);
}
