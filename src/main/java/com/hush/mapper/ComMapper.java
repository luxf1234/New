package com.hush.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hush.pojo.Community;

public interface ComMapper {

	List<Community> find(@Param("comName")String comName);

	@Select("select count(*) from community where comName=#{comName}")
	Integer findByCom(@Param("comName")String comName);

	@Insert("insert into community(comName) values(#{comName})")
	void insert(@Param("comName")String comName);

	@Delete("delete from community where comName=#{comName}")
	void deleteByName(@Param("comName")String comName);


}
