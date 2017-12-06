package ar.com.vga.ignfinity.core.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.endpoint.entities.User;
import ar.com.vga.ignfinity.core.service.ContextService;

@Component
public class ContextServiceImpl implements ContextService{

	private Map<String, IgnfinityContext> map = new HashMap<>();
	
	@Override
	public IgnfinityContext findByUser(User user) {
		return map.get(user.getId());
	}
	
	@Override
	public void put(User user, IgnfinityContext context) {
		map.put(user.getId(), context);
	}

	@Override
	public IgnfinityContext findByUserId(String userid) {
		return map.get(userid);
	}

}
