package com.hush.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hush.pojo.Pic;

public interface PicMapper {

	List<Pic> findImg(@Param("com")String com,
			@Param("floatPeople")String floatPeople,
			@Param("nah")String nah,
			@Param("pah")String pah,
			@Param("realtive")String realtive,
			@Param("starttime")String starttime,
			@Param("endtime")String endtime);

}
