package com.eventmanager.rest.access.backendrestservice.eventList;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.eventmanager.rest.access.backendrestservice.basicAuth.UserInfo;

@Entity
public class Events {
	
	
	@Id
	@GeneratedValue
	private Long eventId;
	
	private String eventName;
	private String description;
	private String duration;
	private String location;
	private long fees;
	private String tags;
	private int maxParticipants;
	private String createdBy;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "event_user",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_name") })
	private Set<UserInfo> userInfo = new HashSet<>();
	
	public Set<UserInfo> getUser() {
		return userInfo;
	}
	public void setUser(Set<UserInfo> user) {
		this.userInfo = user;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (eventId ^ (eventId >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Events other = (Events) obj;
		if (eventId != other.eventId)
			return false;
		return true;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	protected Events() {
		
	}
	public Events(long eventId, String eventName, String description, String duration,String location, long fees,
			String tags, int maxParticipants, String createdBy) {
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
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
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
	
}
