package ar.com.vga.ignfinity.core.endpoint;

import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.endpoint.entities.timeline.TimelineRoot;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

public interface TimelineEndpoint {

	public TimelineRoot timeline(IgnfinityContext context) throws IgnfinityException;
	
}
