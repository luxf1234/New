package com.hush.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hush.pojo.User;

public interface UserMapper {

	@Select("select * from user where id=#{id}")
	User findById(@Param("id")Integer id);

	@Insert("insert into user(name,password) values(#{userName},#{password})")
	void insert(@Param("userName")String userName,@Param("password") String password);

	@Select("select count(*) from user where name=#{userName}")
	Integer selectByName(@Param("userName")String userName);

	@Select("select * from user where name=#{userName} and password=#{password}")
	User login(@Param("userName")String userName,@Param("password") String password);

	@Update("update user set password=#{repassword} where name=#{userName} and "
			+ "password=#{password}")
	void change(@Param("userName")String userName, 
			@Param("password")String password,
			@Param("repassword")String repassword);

	@Select("select id from user where name=#{name}")
	Integer findIdByName(@Param("name")String name);

	@Select("select password from user where name=#{name}")
	String getadmin(@Param("name")String name);

	@Delete("delete from user where name=#{userName} and password=#{password}")
	void delete(@Param("userName")String userName, 
			@Param("password")String password);

}
