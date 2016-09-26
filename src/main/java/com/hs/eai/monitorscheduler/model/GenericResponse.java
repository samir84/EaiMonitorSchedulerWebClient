package com.hs.eai.monitorscheduler.model;


import java.io.Serializable;

import org.springframework.http.HttpStatus;


public class GenericResponse implements Serializable{

	@Override
	public String toString() {
		return "GenericResponse [status=" + status + ", Object=" + Object + ", message=" + message + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private Object Object;
	private String message;
	
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public Object getObject() {
		return Object;
	}
	public void setObject(Object object) {
		Object = object;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
