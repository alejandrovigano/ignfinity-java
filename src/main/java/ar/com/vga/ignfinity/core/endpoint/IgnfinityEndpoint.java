package ar.com.vga.ignfinity.core.endpoint;

import java.util.List;

import org.apache.http.NameValuePair;

import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

public interface IgnfinityEndpoint {

	public String get(String url, IgnfinityContext context) throws IgnfinityException;

	public <T> T  get(String urlTimeline, IgnfinityContext context, Class<T> clazz) throws IgnfinityException;

	public String post(String url, List<NameValuePair> postParameters, IgnfinityContext context) throws IgnfinityException;
	
	public <T> T post(String url, List<NameValuePair> postParameters, IgnfinityContext context, Class<T> clazz) throws IgnfinityException;

	public String getCookie(String string, IgnfinityContext context) throws IgnfinityException; 
	
	public void randomSleep();

	public String getNextPage(String format, String maxId);
}
