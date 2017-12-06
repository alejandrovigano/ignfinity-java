package ar.com.vga.ignfinity.core.endpoint.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.vga.ignfinity.core.endpoint.IgnfinityEndpoint;
import ar.com.vga.ignfinity.core.endpoint.TimelineEndpoint;
import ar.com.vga.ignfinity.core.endpoint.constants.IgnfinityURLS;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.endpoint.entities.timeline.TimelineRoot;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

@Component
public class TimelineEndpointImpl implements TimelineEndpoint{

	private static final Logger log = LoggerFactory.getLogger(TimelineEndpointImpl.class);

	
	@Autowired
	private IgnfinityEndpoint ignfinityEndpoint;
	
	@Override
	public TimelineRoot timeline(IgnfinityContext context) throws IgnfinityException {
		TimelineRoot timeline = ignfinityEndpoint.get(IgnfinityURLS.URL_TIMELINE, context, TimelineRoot.class);
		
		log.info("Response timeline:" + timeline);
		return timeline;
	}

}
