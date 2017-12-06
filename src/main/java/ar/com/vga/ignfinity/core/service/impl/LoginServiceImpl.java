package ar.com.vga.ignfinity.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.vga.ignfinity.core.domain.Ignfinity;
import ar.com.vga.ignfinity.core.endpoint.LoginEndpoint;
import ar.com.vga.ignfinity.core.endpoint.TimelineEndpoint;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.endpoint.entities.timeline.TimelineRoot;
import ar.com.vga.ignfinity.core.endpoint.entities.timeline.TimelineUser;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;
import ar.com.vga.ignfinity.core.repository.IgnfinityRepository;
import ar.com.vga.ignfinity.core.service.ContextService;
import ar.com.vga.ignfinity.core.service.LoginService;

@Component
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginEndpoint loginEndpoint;

	@Autowired
	private TimelineEndpoint timelineEndpoint;

	@Autowired
	private ContextService contextService;

	@Autowired
	private IgnfinityRepository ignfinityRepository;

	@Override
	public IgnfinityContext login(String user, String password) throws IgnfinityException {
		IgnfinityContext context = null;

		boolean logged = false;
		if (ignfinityRepository.findAll().size() > 0) {
			Ignfinity ig = ignfinityRepository.findAll().get(0);
			context = contextService.findByUserId(ig.getUserId());
			if (context != null) {
				logged = true;
			}
		}

		if (!logged) {
			context = loginEndpoint.login(user, password);

			// llamo al inicio, y obetngo el usuario
			TimelineRoot timeline = timelineEndpoint.timeline(context);
			TimelineUser loggedUser = timeline.getGraphql().getUser();

			context.setUser(loggedUser);

			contextService.put(loggedUser, context);

			Ignfinity ig = new Ignfinity();
			ig.setUserId(loggedUser.getId());
			ignfinityRepository.save(ig);
		}

		return context;
	}

	@Override
	public void logout(IgnfinityContext context) throws IgnfinityException {
		loginEndpoint.logout(context);
	}

}
