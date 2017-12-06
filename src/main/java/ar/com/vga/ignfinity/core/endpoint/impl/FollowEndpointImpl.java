package ar.com.vga.ignfinity.core.endpoint.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.vga.ignfinity.core.endpoint.FollowEndpoint;
import ar.com.vga.ignfinity.core.endpoint.IgnfinityEndpoint;
import ar.com.vga.ignfinity.core.endpoint.constants.IgnfinityURLS;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

@Component
public class FollowEndpointImpl implements FollowEndpoint {

	@Autowired
	private IgnfinityEndpoint ignfinityEndpoint;

	@Override
	public void follow(String id, IgnfinityContext context) throws IgnfinityException {
		ignfinityEndpoint.post(String.format(IgnfinityURLS.URL_FOLLOW, id), null, context);
	}

	@Override
	public void unfollow(String id, IgnfinityContext context) throws IgnfinityException {
		ignfinityEndpoint.post(String.format(IgnfinityURLS.URL_UNFOLLOW, id), null, context);
	}
}
