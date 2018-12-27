package com.hush.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface FileMapper {

	@Insert("insert into img(message_id,img) values(#{messageId},#{imageUrlPath})")
	int insert(@Param("messageId")Integer messageId, @Param("imageUrlPath")String imageUrlPath);

	@Select("select id from img where message_id=#{messageId}")
	Integer selectByMessageId(@Param("messageId")Integer messageId);

	@Update("update img set img=#{localFilePath} where message_id=#{messageId}")
	int update(@Param("messageId")Integer messageId, @Param("localFilePath")String localFilePath);

	@Select("select img from img where message_id=#{messageId}")
	String selectImgByMessageId(@Param("messageId")Integer messageId);

}
