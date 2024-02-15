package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

@Table(name="event")
public class Event {
	
	
     @Id
	
	 @Column(name="event_id")
	 private int event_id;

     @Column(name="timeschedule")
     private int timeschedule;

     @Column(name="content")
     private String content;

     @Column(name="line_id")
     private int line_id;

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public int getTimeschedule() {
		return timeschedule;
	}

	public void setTimeschedule(int timeschedule) {
		this.timeschedule = timeschedule;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLine_id() {
		return line_id;
	}

	public void setLine_id(int line_id) {
		this.line_id = line_id;
	}


     
}
