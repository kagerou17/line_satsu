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
    
    @Column(name="pass")
    private String pass;
    
    @Column(name="authority")
    private int authority;
    
    @Column(name="kamokumei")
    private String kamokumei;
    
    @Column(name="gakunen")
    private String gakunen;
    
    @Column(name="class")
    private String classs;

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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public String getKamokumei() {
		return kamokumei;
	}

	public void setKamokumei(String kamokumei) {
		this.kamokumei = kamokumei;
	}

	public String getGakunen() {
		return gakunen;
	}

	public void setGakunen(String gakunen) {
		this.gakunen = gakunen;
	}

	public String getClasss() {
		return classs;
	}

	public void setClasss(String classs) {
		this.classs = classs;
	}

	
}
