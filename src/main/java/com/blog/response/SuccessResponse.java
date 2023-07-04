package com.blog.response;

import org.springframework.http.HttpStatusCode;

public class SuccessResponse implements Response {
	private HttpStatusCode status;
	private String message;
	private Object data;

	// Constructors, getters, and setters
	public SuccessResponse(HttpStatusCode status, String message) {
		this.status = status;
		this.message = message;
	}

	public SuccessResponse(HttpStatusCode status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	@Override
	public HttpStatusCode getStatus() {
		return status;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public Object getData() {
		return data;
	}
}

