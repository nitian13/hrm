package org.nb.hrm.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.nb.hrm.net.param.CourseParam;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface CourseMapper {
    //添加
    @Insert("insert into t_course (duration,content,name,teacher) values(#{duration},#{content},#{name},#{teacher})")
    @Options( keyProperty = "id", keyColumn = "id")
    int add(CourseParam courseParam);
}
