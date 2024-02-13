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
private int nikka_id;
@Column(name="kamoku_id")
private int kamoku_id;
@Column(name="kamokumei")
private String kamokumei;
@Column(name="getu1")
private int getu1;
@Column(name="getu2")
private int getu2;
@Column(name="getu3")
private int getu3;
@Column(name="getu4")
private int getu4;
@Column(name="ka1")
private int ka1;
@Column(name="ka2")
private int ka2;
@Column(name="ka3")
private int ka3;
@Column(name="ka4")
private int ka4;
@Column(name="sui1")
	private int sui1;
@Column(name="sui2")
private int sui2;
@Column(name="sui3")
private int sui3;
@Column(name="sui4")
private int sui4;
@Column(name="moku1")
private int moku1;
@Column(name="moku2")
private int moku2;
@Column(name="moku3")
private int moku3;
@Column(name="moku4")
private int moku4;
@Column(name="kin1")
private int kin1;	
@Column(name="kin2")
private int kin2;
@Column(name="kin3")
private int kin3;
@Column(name="kin4")
private int kin4;
public int getNikka_id() {
	return nikka_id;
}
public void setNikka_id(int nikka_id) {
	this.nikka_id = nikka_id;
}
public int getKamoku_id() {
	return kamoku_id;
}
public void setKamoku_id(int kamoku_id) {
	this.kamoku_id = kamoku_id;
}
public String getKamokumei() {
	return kamokumei;
}
public void setKamokumei(String kamokumei) {
	this.kamokumei = kamokumei;
}
public int getGetu1() {
	return getu1;
}
public void setGetu1(int getu1) {
	this.getu1 = getu1;
}
public int getGetu2() {
	return getu2;
}
public void setGetu2(int getu2) {
	this.getu2 = getu2;
}
public int getGetu3() {
	return getu3;
}
public void setGetu3(int getu3) {
	this.getu3 = getu3;
}
public int getGetu4() {
	return getu4;
}
public void setGetu4(int getu4) {
	this.getu4 = getu4;
}
public int getKa1() {
	return ka1;
}
public void setKa1(int ka1) {
	this.ka1 = ka1;
}
public int getKa2() {
	return ka2;
}
public void setKa2(int ka2) {
	this.ka2 = ka2;
}
public int getKa3() {
	return ka3;
}
public void setKa3(int ka3) {
	this.ka3 = ka3;
}
public int getKa4() {
	return ka4;
}
public void setKa4(int ka4) {
	this.ka4 = ka4;
}
public int getSui1() {
	return sui1;
}
public void setSui1(int sui1) {
	this.sui1 = sui1;
}
public int getSui2() {
	return sui2;
}
public void setSui2(int sui2) {
	this.sui2 = sui2;
}
public int getSui3() {
	return sui3;
}
public void setSui3(int sui3) {
	this.sui3 = sui3;
}
public int getSui4() {
	return sui4;
}
public void setSui4(int sui4) {
	this.sui4 = sui4;
}
public int getMoku1() {
	return moku1;
}
public void setMoku1(int moku1) {
	this.moku1 = moku1;
}
public int getMoku2() {
	return moku2;
}
public void setMoku2(int moku2) {
	this.moku2 = moku2;
}
public int getMoku3() {
	return moku3;
}
public void setMoku3(int moku3) {
	this.moku3 = moku3;
}
public int getMoku4() {
	return moku4;
}
public void setMoku4(int moku4) {
	this.moku4 = moku4;
}
public int getKin1() {
	return kin1;
}
public void setKin1(int kin1) {
	this.kin1 = kin1;
}
public int getKin2() {
	return kin2;
}
public void setKin2(int kin2) {
	this.kin2 = kin2;
}
public int getKin3() {
	return kin3;
}
public void setKin3(int kin3) {
	this.kin3 = kin3;
}
public int getKin4() {
	return kin4;
}
public void setKin4(int kin4) {
	this.kin4 = kin4;
}

	
}
