package com.eventmanager.rest.access.backendrestservice.response;

public class Response {
	
	private Boolean success;
	private String reason;
	
	Response () {
		
	}
	public Response(Boolean success, String reason) {
		super();
		this.success = success;
		this.reason = reason;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	

}
