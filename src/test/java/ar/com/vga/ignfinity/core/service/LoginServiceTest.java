package ar.com.vga.ignfinity.core.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTest {

	@Autowired
	private LoginService loginService;
	
	@Test
	public void testLogin() throws IgnfinityException{
		IgnfinityContext context = loginService.login("test10262017", "qwer1234");
		
		assertNotNull(context.getUser());
		
		loginService.logout(context);
	}
	
	
}
