package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="school")
public class School {
@Id
@Column(name="gakka_id")
private int gakka_id;
@Column(name="department")
private String department;
@Column(name="grade")
private String grade;
@Column(name="dass")
private String dass;
public int getGakka_id() {
	return gakka_id;
}
public void setGakka_id(int gakka_id) {
	this.gakka_id = gakka_id;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public String getGrade() {
	return grade;
}
public void setGrade(String grade) {
	this.grade = grade;
}
public String getDass() {
	return dass;
}
public void setDass(String dass) {
	this.dass = dass;
}

}
