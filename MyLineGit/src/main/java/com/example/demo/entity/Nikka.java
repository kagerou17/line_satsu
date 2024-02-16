package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

@Table(name="nikka")
public class Nikka {
	
	@Id
	
	@Column(name="nikka_id")
	public int nikka_id;
	
	@Column(name="gakunen")
	private String gakunen;
	
	@Column(name="class")
	private String classs;
	
	@Column(name="kamokumei")
	private String kamokumei;
	
	@Column(name="getu1")
	private String getu1;
	
	@Column(name="getu2")
	private String getu2;
	
	@Column(name="getu3")
	private String getu3;
	
	@Column(name="getu4")
	private String getu4;
	
	@Column(name="ka1")
	private String ka1;
	
	@Column(name="ka2")
	private String ka2;
	
	@Column(name="ka3")
	private String ka3;
	
	@Column(name="ka4")
	private String ka4;
	
	@Column(name="sui1")
	private String sui1;
	
	@Column(name="sui2")
	private String sui2;
	
	@Column(name="sui3")
	private String sui3;
	
	@Column(name="sui4")
	private String sui4;
	
	@Column(name="moku1")
	private String moku1;
	
	@Column(name="moku2")
	private String moku2;
	
	@Column(name="moku3")
	private String moku3;
	
	@Column(name="moku4")
	private String moku4;
	
	@Column(name="kin1")
	private String kin1;
	
	@Column(name="kin2")
	private String kin2;

	@Column(name="kin3")
	private String kin3;
	
	@Column(name="kin4")
	private String kin4;

	public int getNikka_id() {
		return nikka_id;
	}

	public void setNikka_id(int nikka_id) {
		this.nikka_id = nikka_id;
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

	public String getKamokumei() {
		return kamokumei;
	}

	public void setKamokumei(String kamokumei) {
		this.kamokumei = kamokumei;
	}

	public String getGetu1() {
		return getu1;
	}

	public void setGetu1(String getu1) {
		this.getu1 = getu1;
	}

	public String getGetu2() {
		return getu2;
	}

	public void setGetu2(String getu2) {
		this.getu2 = getu2;
	}

	public String getGetu3() {
		return getu3;
	}

	public void setGetu3(String getu3) {
		this.getu3 = getu3;
	}

	public String getGetu4() {
		return getu4;
	}

	public void setGetu4(String getu4) {
		this.getu4 = getu4;
	}

	public String getKa1() {
		return ka1;
	}

	public void setKa1(String ka1) {
		this.ka1 = ka1;
	}

	public String getKa2() {
		return ka2;
	}

	public void setKa2(String ka2) {
		this.ka2 = ka2;
	}

	public String getKa3() {
		return ka3;
	}

	public void setKa3(String ka3) {
		this.ka3 = ka3;
	}

	public String getKa4() {
		return ka4;
	}

	public void setKa4(String ka4) {
		this.ka4 = ka4;
	}

	public String getSui1() {
		return sui1;
	}

	public void setSui1(String sui1) {
		this.sui1 = sui1;
	}

	public String getSui2() {
		return sui2;
	}

	public void setSui2(String sui2) {
		this.sui2 = sui2;
	}

	public String getSui3() {
		return sui3;
	}

	public void setSui3(String sui3) {
		this.sui3 = sui3;
	}

	public String getSui4() {
		return sui4;
	}

	public void setSui4(String sui4) {
		this.sui4 = sui4;
	}

	public String getMoku1() {
		return moku1;
	}

	public void setMoku1(String moku1) {
		this.moku1 = moku1;
	}

	public String getMoku2() {
		return moku2;
	}

	public void setMoku2(String moku2) {
		this.moku2 = moku2;
	}

	public String getMoku3() {
		return moku3;
	}

	public void setMoku3(String moku3) {
		this.moku3 = moku3;
	}

	public String getMoku4() {
		return moku4;
	}

	public void setMoku4(String moku4) {
		this.moku4 = moku4;
	}

	public String getKin1() {
		return kin1;
	}

	public void setKin1(String kin1) {
		this.kin1 = kin1;
	}

	public String getKin2() {
		return kin2;
	}

	public void setKin2(String kin2) {
		this.kin2 = kin2;
	}

	public String getKin3() {
		return kin3;
	}

	public void setKin3(String kin3) {
		this.kin3 = kin3;
	}

	public String getKin4() {
		return kin4;
	}

	public void setKin4(String kin4) {
		this.kin4 = kin4;
	}


	


}
