package ar.com.vga.ignfinity.core.endpoint;

import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

public interface FollowEndpoint {

	void follow(String id, IgnfinityContext context) throws IgnfinityException;

	void unfollow(String id, IgnfinityContext context) throws IgnfinityException;

}
