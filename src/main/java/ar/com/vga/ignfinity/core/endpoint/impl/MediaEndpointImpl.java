package ar.com.vga.ignfinity.core.endpoint.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.vga.ignfinity.core.endpoint.IgnfinityEndpoint;
import ar.com.vga.ignfinity.core.endpoint.MediaEndpoint;
import ar.com.vga.ignfinity.core.endpoint.constants.IgnfinityURLS;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.endpoint.entities.media.MediaDetailRoot;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

@Component
public class MediaEndpointImpl implements MediaEndpoint {
	
	private static final Logger log = LoggerFactory.getLogger(MediaEndpointImpl.class);
	
	@Autowired
	private IgnfinityEndpoint ignfinityEndpoint;
	
	@Override
	public MediaDetailRoot mediaDetail(String id, IgnfinityContext context) throws IgnfinityException {
		MediaDetailRoot detail = ignfinityEndpoint.get( String.format(IgnfinityURLS.URL_MEDIA,id), context, MediaDetailRoot.class);
		return detail;
	}
}
