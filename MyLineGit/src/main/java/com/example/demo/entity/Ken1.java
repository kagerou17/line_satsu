package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ken1")
public class Ken1 {
@Id
@Column(name="kamoku_id")
private int kamoku_id;
@Column(name="kamoku")
private String kamoku;
public int getKamoku_id() {
	return kamoku_id;
}
public void setKamoku_id(int kamoku_id) {
	this.kamoku_id = kamoku_id;
}
public String getKamoku() {
	return kamoku;
}
public void setKamoku(String kamoku) {
	this.kamoku = kamoku;
}


}
