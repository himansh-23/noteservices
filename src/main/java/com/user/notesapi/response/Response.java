package com.user.notesapi.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
	private int statusCode;
	private String statusMessage;
	
	@Override
	public String toString() {
		return "Response [statusCode=" + statusCode + ", statusMessage=" + statusMessage + "]";
	}
	
}
