package com.final_assignment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UpdateMapper {
    @Update("UPDATE your_table SET name = #{entity.name} WHERE id = #{entity.id}")
    void update(@Param("entity") Entity entity);
}
