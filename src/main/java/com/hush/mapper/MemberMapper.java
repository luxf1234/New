package com.hush.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hush.pojo.Message;

public interface MemberMapper {

	@Insert("insert into message(userName,"
			+ "com,tel,idcard,car,address,floatPeople,"
			+ "realtive,nah,pah,creattime) values(#{userName},"
			+ "#{com},#{tel},#{idcard},#{car},"
			+ "#{address},#{floatPeople},#{realtive},#{nah},#{pah},#{creattime})")
	void insert(Message message);

	@Update("update message set "
			+ "userName=#{userName},com=#{com},tel=#{tel},car=#{car},address=#{address},floatPeople=#{floatPeople},"
			+ "realtive=#{realtive},nah=#{nah},pah=#{pah},creattime=#{creattime} where idcard=#{idcard}")
	void update(Message message);

	@Select("select id from message where idcard=#{idcard}")
	Integer findIdByIdcard(@Param("idcard")String idcard);

	@Select("select * from message where userName like concat(concat('%',#{userName}),'%')")
	List<Message> findByUserName(@Param("userName")String userName);
	
	@Select("select count(1) from message where idcard=#{idcard}")
	Integer compareIdcard(@Param("idcard")String idcard);

}
