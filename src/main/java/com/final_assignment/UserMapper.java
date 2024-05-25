package com.final_assignment;

import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM names")
    List<User> findAll();

    @Select("SELECT * FROM names WHERE name LIKE CONCAT(#{prefix}, '%')")
    List<User> findByNameStartingWith(String prefix);

    @Select("SELECT id, name FROM names WHERE id = #{id}")
    Optional<User> findName(int id);

    @Insert("INSERT INTO users (name, email) VALUES (#{name}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Update("UPDATE users SET name = #{name}, email = #{email} WHERE id = #{id}")
    void update(Integer id, String name, String email);

}
