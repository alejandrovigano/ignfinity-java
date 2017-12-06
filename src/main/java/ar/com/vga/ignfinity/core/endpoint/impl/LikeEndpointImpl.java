package ar.com.vga.ignfinity.core.endpoint.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.vga.ignfinity.core.endpoint.IgnfinityEndpoint;
import ar.com.vga.ignfinity.core.endpoint.LikeEndpoint;
import ar.com.vga.ignfinity.core.endpoint.constants.IgnfinityURLS;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

@Component
public class LikeEndpointImpl implements LikeEndpoint{

	
	@Autowired
	private IgnfinityEndpoint ignfinityEndpoint;
	
	@Override
	public void like(String id, IgnfinityContext context) throws IgnfinityException {
		ignfinityEndpoint.post(String.format(IgnfinityURLS.URL_LIKES,id), null, context);
	}

	@Override
	public void unlike(String id, IgnfinityContext context) throws IgnfinityException {
		ignfinityEndpoint.post(String.format(IgnfinityURLS.URL_UNLIKE,id), null, context);		
	}

}
