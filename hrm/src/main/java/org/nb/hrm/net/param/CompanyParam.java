package org.nb.hrm.net.param;

import lombok.Data;


@Data
public class CompanyParam {

    private String name;
    //地址
    private String address;

    //类型，0公司、2培训机构
    private int type;
}
