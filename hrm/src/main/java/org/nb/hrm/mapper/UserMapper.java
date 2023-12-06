package org.nb.hrm.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.nb.hrm.entity.User;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper {

    /*增加*/
    @Insert("insert into t_user (phone,role) values(#{phone},#{role})")
    @Options( keyProperty = "id", keyColumn = "id")
    int register(String phone,int role);

    //通过电话查询用户
    @Select("select * from t_user where phone=#{phone}")
    User findUser(String phone);

    //通过电话和角色来登录
    @Select("select * from t_user where phone=#{phone} and role=#{role}")
    User login(String phone,int role);
}
