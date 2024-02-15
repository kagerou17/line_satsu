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
	
	@Column(name="line_id")
	private int line_id;
	
	@Column(name="subject_time")
	private int subject_time;
	
	@Column(name="subject")
	private String subject;
	
	@Column(name="note")
	private int note;

	public int getNote_id() {
		return note_id;
	}

	public void setNote_id(int note_id) {
		this.note_id = note_id;
	}

	public int getLine_id() {
		return line_id;
	}

	public void setLine_id(int line_id) {
		this.line_id = line_id;
	}

	public int getSubject_time() {
		return subject_time;
	}

	public void setSubject_time(int subject_time) {
		this.subject_time = subject_time;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}
	
	
	
}
