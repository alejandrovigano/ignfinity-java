package ar.com.vga.ignfinity.core.endpoint.entities;

import java.util.Map;

import org.apache.http.client.protocol.HttpClientContext;

public class IgnfinityContext {

	private User user;
	private Map<String, String> headers;
	private HttpClientContext httpContext;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public HttpClientContext getHttpContext() {
		return httpContext;
	}

	public void setHttpContext(HttpClientContext httpContext) {
		this.httpContext = httpContext;
	}

}
