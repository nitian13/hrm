package org.nb.hrm.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.nb.hrm.entity.Company;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface CompanyMapper {

    /*增加*/
    @Insert("insert into t_company (name,address,registerTime,phone,type) values(#{name},#{address},#{registerTime},#{phone},#{type})")
    @Options(keyProperty = "id", keyColumn = "id")
    int register(String name, String address, Long registerTime, String phone, int type);

    //通过id查询公司
    @Select("select * from t_company where id=#{id}")
    Company findCompanyById(Long id);
}
