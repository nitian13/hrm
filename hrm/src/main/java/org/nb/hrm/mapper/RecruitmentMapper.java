package org.nb.hrm.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.nb.hrm.entity.Recruitment;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface RecruitmentMapper {
    /*增加*/
    @Insert("insert into t_recruitment (wage,workingHour,description,address) values(#{wage},#{workingHour},#{description},#{address})")
    @Options( keyProperty = "id", keyColumn = "id")
    int add(double wage,int workingHour,String description,String address);

    //通过钱去查询
    @Select("SELECT * FROM t_recruitment WHERE wage >#{wage}")
    List<Recruitment> findJobByMoney(double wage);

    //id查询
    @Select("select * from t_recruitment where id=#{id}")
    Recruitment findById(Long id);

    //查询所有
    @Select("select * from t_recruitment")
    List<Recruitment> getRecruitmentList();
}
