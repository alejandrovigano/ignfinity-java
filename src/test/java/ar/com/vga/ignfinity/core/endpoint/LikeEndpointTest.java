package ar.com.vga.ignfinity.core.endpoint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LikeEndpointTest {

	@Autowired
	private LoginEndpoint loginEndpoint;
	@Autowired
	private LikeEndpoint like;
	@Autowired
	private IgnfinityEndpoint ignfinityEndpoint;
	
	private IgnfinityContext context;

	@Test
	public void testLogin() throws IgnfinityException {
		like.like("1632875661407573318", context);
		ignfinityEndpoint.randomSleep();
		like.unlike("1632875661407573318", context);
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
