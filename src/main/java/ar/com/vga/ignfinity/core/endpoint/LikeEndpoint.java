package ar.com.vga.ignfinity.core.endpoint;

import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

public interface LikeEndpoint {

	public void like(String id, IgnfinityContext context) throws IgnfinityException;
	
	public void unlike(String id, IgnfinityContext context) throws IgnfinityException;
}
