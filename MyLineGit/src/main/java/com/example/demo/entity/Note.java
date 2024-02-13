package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="note")
public class Note {
@Id
@Column(name="note_id")
private int note_id;
@Column(name="subject_time")
private String subject_time;
@Column(name="subject")
private String subject;
@Column(name="note")
private String note;
public int getNote_id() {
	return note_id;
}
public void setNote_id(int note_id) {
	this.note_id = note_id;
}
public String getSubject_time() {
	return subject_time;
}
public void setSubject_time(String subject_time) {
	this.subject_time = subject_time;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}


}
