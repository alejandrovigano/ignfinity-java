package ar.com.vga.ignfinity.core.service;

import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.endpoint.entities.User;

public interface ContextService {

	IgnfinityContext findByUser(User user);

	IgnfinityContext findByUserId(String userid);
	
	void put(User user, IgnfinityContext context);
	
}
