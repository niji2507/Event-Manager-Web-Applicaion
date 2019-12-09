package com.eventmanager.rest.access.backendrestservice.eventList;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.eventmanager.rest.access.backendrestservice.basicAuth.UserNotification;

@Entity
public class Notification {
	@Id
	@GeneratedValue
	private Long notificationId;
	private Long eventId;
	private String action;
	private String eventName;
	
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	Notification() {
		
	}
	
	public Notification(Long notificationId, Long eventId, String action,String eventName) {
		super();
		this.notificationId = notificationId;
		this.eventId = eventId;
		this.action = action;
		this.eventName = eventName;
	}

	public Long getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	

}
