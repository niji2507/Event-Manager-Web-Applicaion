package com.eventmanager.rest.access.backendrestservice.basicAuth;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

import com.eventmanager.rest.access.backendrestservice.eventList.Notification;


@Entity
public class UserNotification  {
	
	@Id
	@GeneratedValue
    private Long entry;

	private String username;

    private Long notificationId;    
    
    private Boolean markasread;
    
    public UserNotification() {
		
	}
    
    public Long getEntry() {
		return entry;
	}

	public void setEntry(Long entry) {
		this.entry = entry;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public Boolean getMarkasread() {
		return markasread;
	}

	public void setMarkasread(Boolean markasread) {
		this.markasread = markasread;
	}

	public UserNotification(Long entry, String username, Long notificationId, Boolean markasread) {
		super();
		this.entry = entry;
		this.username = username;
		this.notificationId = notificationId;
		this.markasread = markasread;
	}

	
    
}
