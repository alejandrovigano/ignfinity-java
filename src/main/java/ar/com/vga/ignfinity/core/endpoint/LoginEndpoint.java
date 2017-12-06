package ar.com.vga.ignfinity.core.endpoint;

import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

public interface LoginEndpoint {

	IgnfinityContext login(String user, String password) throws IgnfinityException;

	void logout(IgnfinityContext context) throws IgnfinityException;
	
}
