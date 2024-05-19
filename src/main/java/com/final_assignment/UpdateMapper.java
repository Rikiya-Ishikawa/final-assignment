package com.final_assignment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Optional;

@Mapper
public interface UpdateMapper {
    @Update("UPDATE users SET name = #{name}, email = #{email} WHERE id = #{id}")
    int update(Integer id, String name, String email);

    @Select("SELECT * FROM users WHERE id = #{id}")
    Entity findById(int id);


}
