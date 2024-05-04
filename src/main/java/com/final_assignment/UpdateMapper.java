package com.final_assignment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UpdateMapper {
    @Update("UPDATE users SET name = #{name}, email = #{email} WHERE id = #{id}")
    static void updateEntity(Entity entity);
}
