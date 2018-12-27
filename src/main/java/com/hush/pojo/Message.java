package com.hush.pojo;


import java.io.Serializable;

public class Message implements Serializable{
	private Integer id;
	private String userName;
	private String com;
	private String tel;
	private String idcard;
	private String car;
	private String address;
	private String floatPeople;
	private String realtive;
	private String nah;
	private String pah;
	private Integer creattime;
	public Integer getCreattime() {
		return creattime;
	}
	public void setCreattime(Integer creattime) {
		this.creattime=creattime;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFloatPeople() {
		return floatPeople;
	}
	public void setFloatPeople(String floatPeople) {
		this.floatPeople = floatPeople;
	}
	public String getRealtive() {
		return realtive;
	}
	public void setRealtive(String realtive) {
		this.realtive = realtive;
	}
	public String getNah() {
		return nah;
	}
	public void setNah(String nap) {
		this.nah = nap;
	}
	public String getPah() {
		return pah;
	}
	public void setPah(String pah) {
		this.pah = pah;
	}

	
}
