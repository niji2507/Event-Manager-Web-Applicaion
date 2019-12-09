package com.eventmanager.rest.access.backendrestservice.eventList;

import java.util.List;

import com.eventmanager.rest.access.backendrestservice.basicAuth.UserInfo;

public class EventResponse {
	

	private Long eventId;
	private String eventName;
	private String description;
	private String duration;
	private String location;
	private long fees;
	private String tags;
	private int maxParticipants;
	private String createdBy;
	private boolean isRegistereduser;
	private List<String> userList;
	
	public List<String> getUserList() {
		return userList;
	}

	public void setUserList(List<String> userList) {
		this.userList = userList;
	}

	EventResponse() {
		
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getFees() {
		return fees;
	}

	public void setFees(long fees) {
		this.fees = fees;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isRegistereduser() {
		return isRegistereduser;
	}

	public void setRegistereduser(boolean isRegistereduser) {
		this.isRegistereduser = isRegistereduser;
	}

	public EventResponse(Long eventId, String eventName, String description, String duration, String location,
			long fees, String tags, int maxParticipants, String createdBy, boolean isRegistereduser) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.description = description;
		this.duration = duration;
		this.location = location;
		this.fees = fees;
		this.tags = tags;
		this.maxParticipants = maxParticipants;
		this.createdBy = createdBy;
		this.isRegistereduser = isRegistereduser;
	}
	
	

}
