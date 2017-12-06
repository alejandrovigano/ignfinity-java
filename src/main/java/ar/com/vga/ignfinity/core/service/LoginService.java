package ar.com.vga.ignfinity.core.service;

import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

public interface LoginService {

	void logout(IgnfinityContext context) throws IgnfinityException;
 
	IgnfinityContext login(String user, String password) throws IgnfinityException;

}
