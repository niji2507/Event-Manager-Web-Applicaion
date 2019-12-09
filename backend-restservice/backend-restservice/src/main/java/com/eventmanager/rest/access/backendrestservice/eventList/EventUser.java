package com.eventmanager.rest.access.backendrestservice.eventList;

public class EventUser {
	private Long eventId;
	private String username;
	
	EventUser() {
		
	}
	public EventUser(Long eventId, String username) {
		super();
		this.eventId = eventId;
		this.username = username;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	

}
