package com.final_assignment;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users")
    List<User> findAll();

    @Select("SELECT * FROM users WHERE name LIKE CONCAT(#{prefix}, '%')")
    List<User> findByNameStartingWith(String prefix);

    @Select("SELECT * FROM users WHERE id = #{id}")
    Optional<User> findById(int id);

    @Insert("INSERT INTO users (name, email) VALUES (#{name}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Update("UPDATE users SET name = #{name}, email = #{email} WHERE id = #{id}")
    void update(Integer id, String name, String email);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void delete(Integer id);
}
