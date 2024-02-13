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
private String timeschedule;
@Column(name="content")
private String content;
@Column(name="acount_id")
private int acount_id;
public int getEvent_id() {
	return event_id;
}
public void setEvent_id(int event_id) {
	this.event_id = event_id;
}
public String getTimeschedule() {
	return timeschedule;
}
public void setTimeschedule(String timeschedule) {
	this.timeschedule = timeschedule;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public int getAcount_id() {
	return acount_id;
}
public void setAcount_id(int acount_id) {
	this.acount_id = acount_id;
}


}
