package com.blog.response;

import org.springframework.http.HttpStatusCode;

public class ErrorResponse implements Response {
	private HttpStatusCode status;
	private String message;
	private Object error;
	
	public ErrorResponse(HttpStatusCode status, String message) {
		this.status = status;
		this.message = message;
	}

	public ErrorResponse(HttpStatusCode status, String message, Object error) {
		this.status = status;
		this.message = message;
		this.error = error;
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
		return error;
	}
}