package ar.com.vga.ignfinity.core.endpoint.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.vga.ignfinity.core.endpoint.IgnfinityEndpoint;
import ar.com.vga.ignfinity.core.endpoint.constants.IgnfinityURLS;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

@Component
public class IgnfinityEndpointImpl implements IgnfinityEndpoint {

	private static final Logger log = LoggerFactory.getLogger(IgnfinityEndpointImpl.class);

	@Autowired
	private ObjectMapper objectMapper;

	private String call(HttpRequestBase request, IgnfinityContext context) throws IgnfinityException {

		synchronized (context) {
			this.randomSleep();
			try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

				Map<String, String> headers = updateAndGetHeaders(context);
				HttpClientContext httpContext = updateAndGetHttpcontext(context);

				headers.forEach(request::addHeader);

				CloseableHttpResponse response = httpClient.execute(request, httpContext);

				int statusCode = response.getStatusLine().getStatusCode();
				log.info(request.getMethod() + " to " + request.getURI() + " Status Code: " + statusCode);
				if (statusCode != 200) {
					throw new IgnfinityException("Error, codigo de respuesta: " + statusCode);
				}

				String responseAsString = EntityUtils.toString(response.getEntity());
				log.info(" - Response Body: " + responseAsString);

				return responseAsString;
			} catch (Exception e) {
				throw new IgnfinityException(e);
			}
		}
	}

	@Override
	public String get(String url, IgnfinityContext context) throws IgnfinityException {
		HttpGet httpGet = new HttpGet(url);
		return call(httpGet, context);
	}

	@Override
	public <T> T get(String url, IgnfinityContext context, Class<T> clazz) throws IgnfinityException {

		String response = get(url, context);
		try {
			return objectMapper.readValue(response, clazz);
		} catch (Exception e) {
			throw new IgnfinityException(e);
		}
	}

	@Override
	public String getCookie(String string, IgnfinityContext context) throws IgnfinityException {
		HttpClientContext httpContext = context.getHttpContext();

		CookieStore cookieStore = httpContext.getCookieStore();
		Optional<Cookie> cookieOptional = cookieStore.getCookies().stream().filter(x -> "csrftoken".equals(x.getName()))
				.findFirst();
		if (!cookieOptional.isPresent()) {
			throw new IgnfinityException("No esta la cookie csrftoken");
		}

		return cookieOptional.get().getValue();
	}

	private Map<String, String> headers() {
		Map<String, String> headers = new HashMap<>();
		headers.put("Accept-Encoding", "gzip, deflate");
		headers.put("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");
		headers.put("Connection", "keep-alive");
		headers.put("Host", "www.instagram.com");
		headers.put("Origin", "https://www.instagram.com");
		headers.put("Referer", "https://www.instagram.com/");
		headers.put("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.103 Safari/537.36");
		headers.put("X-Instagram-AJAX", "1");
		headers.put("X-Requested-With", "XMLHttpRequest");

		return headers;
	}

	@Override
	public String post(String url, List<NameValuePair> postParameters, IgnfinityContext context)
			throws IgnfinityException {
		HttpPost httpPost = new HttpPost(url);
		if (postParameters != null) {
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				throw new IgnfinityException(e);
			}
		}
		return call(httpPost, context);
	}

	@Override
	public <T> T post(String url, List<NameValuePair> postParameters, IgnfinityContext context, Class<T> clazz)
			throws IgnfinityException {
		String response = post(url, postParameters, context);
		try {
			return objectMapper.readValue(response, clazz);
		} catch (Exception e) {
			throw new IgnfinityException(e);
		}
	}

	@Override
	public void randomSleep() {
		try {
			Thread.sleep(new Random(new Date().getTime()).nextInt(5) * 1000);
		} catch (InterruptedException e) {
			log.error("Error al obtener random");
		}
	}

	private Map<String, String> updateAndGetHeaders(IgnfinityContext context) {
		// seteo los headers
		if (CollectionUtils.isEmpty(context.getHeaders())) {
			context.setHeaders(headers());
		}
		Map<String, String> headers = context.getHeaders();
		return headers;
	}

	private HttpClientContext updateAndGetHttpcontext(IgnfinityContext context) {
		if (context.getHttpContext() == null) {
			context.setHttpContext(HttpClientContext.create());
		}
		return context.getHttpContext();
	}

	@Override
	public String getNextPage(String url, String maxId) {
		return url + "&" + IgnfinityURLS.MAX_ID_PARAM + "=" + maxId;
	}

}
