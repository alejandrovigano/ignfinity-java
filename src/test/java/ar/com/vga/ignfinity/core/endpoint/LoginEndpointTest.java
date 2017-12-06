package ar.com.vga.ignfinity.core.endpoint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginEndpointTest {

	@Autowired
	private LoginEndpoint loginEndpoint;
	
	@Test
	public void testLogin() throws IgnfinityException{
		IgnfinityContext context = loginEndpoint.login("test10262017", "qwer1234");
		loginEndpoint.logout(context);
	}
	
	
}
