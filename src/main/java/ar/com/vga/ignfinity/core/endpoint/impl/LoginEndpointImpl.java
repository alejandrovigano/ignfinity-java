package ar.com.vga.ignfinity.core.endpoint.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.vga.ignfinity.core.endpoint.IgnfinityEndpoint;
import ar.com.vga.ignfinity.core.endpoint.LoginEndpoint;
import ar.com.vga.ignfinity.core.endpoint.constants.IgnfinityURLS;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

@Component
public class LoginEndpointImpl implements LoginEndpoint {

	private static final Logger log = LoggerFactory.getLogger(LoginEndpointImpl.class);

	@Autowired
	private IgnfinityEndpoint ignfinityEndpoint;

	@Override
	public IgnfinityContext login(String user, String password) throws IgnfinityException {
		// inicio
		IgnfinityContext context = new IgnfinityContext();
		ignfinityEndpoint.get(IgnfinityURLS.URL, context);

		String csrftoken = ignfinityEndpoint.getCookie("csrftoken", context);
		log.info("csrftoken cookie: " + csrftoken);

		context.getHeaders().put("X-CSRFToken", csrftoken);

		List<NameValuePair> postParameters = new ArrayList<>();
		postParameters.add(new BasicNameValuePair("username", user));
		postParameters.add(new BasicNameValuePair("password", password));

		String response = ignfinityEndpoint.post(IgnfinityURLS.URL_LOGIN, postParameters, context);
		log.info("csrftoken cookie: " + response);
		
		csrftoken = ignfinityEndpoint.getCookie("csrftoken", context);
		log.info("csrftoken cookie: " + csrftoken);

		context.getHeaders().put("X-CSRFToken", csrftoken);

		return context;
	}

	@Override
	public void logout(IgnfinityContext context) throws IgnfinityException {

		String csrftoken = ignfinityEndpoint.getCookie("csrftoken", context);

		List<NameValuePair> postParameters = new ArrayList<>();
		postParameters.add(new BasicNameValuePair("csrfmiddlewaretoken", csrftoken));

		ignfinityEndpoint.post(IgnfinityURLS.URL_LOGOUT, postParameters, context);
	}

}
