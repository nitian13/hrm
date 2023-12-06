package org.nb.hrm.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.nb.hrm.entity.Collection;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CollectionMapper {
    //添加
    @Insert("insert into t_collection (userId,recruitmentId,time) values(#{userId},#{recruitmentId},#{time})")
    @Options( keyProperty = "id", keyColumn = "id")
    int add(Collection collection);

}
