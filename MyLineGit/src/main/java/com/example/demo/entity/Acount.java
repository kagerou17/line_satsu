package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="acount")
public class Acount {
@Id
@Column(name="acount_id")
private int acount_id;
@Column(name="acount_name")
private String acount_name;
@Column(name="line_id")
private String line_id;
@Column(name="authority")
private String authority;
@Column(name="nikkatime")
private String nikkatime;
public int getAcount_id() {
	return acount_id;
}
public void setAcount_id(int acount_id) {
	this.acount_id = acount_id;
}
public String getAcount_name() {
	return acount_name;
}
public void setAcount_name(String acount_name) {
	this.acount_name = acount_name;
}
public String getLine_id() {
	return line_id;
}
public void setLine_id(String line_id) {
	this.line_id = line_id;
}
public String getAuthority() {
	return authority;
}
public void setAuthority(String authority) {
	this.authority = authority;
}
public String getNikkatime() {
	return nikkatime;
}
public void setNikkatime(String nikkatime) {
	this.nikkatime = nikkatime;
}


	
}
