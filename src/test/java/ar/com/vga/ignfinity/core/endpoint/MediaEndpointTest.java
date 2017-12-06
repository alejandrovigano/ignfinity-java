package ar.com.vga.ignfinity.core.endpoint;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.endpoint.entities.media.MediaDetailRoot;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MediaEndpointTest {

	@Autowired
	private LoginEndpoint loginEndpoint;

	@Autowired
	private MediaEndpoint media;

	private IgnfinityContext context;

	@Test
	public void testLogin() throws IgnfinityException {
		MediaDetailRoot detail = media.mediaDetail("Bat0XHuFSfR", context);
		assertNotNull(detail);
	}

	@Before
	public void login() throws IgnfinityException {
		context = loginEndpoint.login("test10262017", "qwer1234");
	}

	@After
	public void logout() throws IgnfinityException {
		loginEndpoint.logout(context);
	}

}
