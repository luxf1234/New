package com.hush.pojo;

import java.io.Serializable;

public class Community implements Serializable{
	
	
	private Integer id;
	private String comName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	
}
