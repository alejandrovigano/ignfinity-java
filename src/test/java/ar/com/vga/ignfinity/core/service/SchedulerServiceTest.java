package ar.com.vga.ignfinity.core.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.endpoint.entities.media.Media;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;
import ar.com.vga.ignfinity.core.service.impl.SchedulerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchedulerServiceTest {

	@Autowired
	private LoginService loginService;
	
	private IgnfinityContext context;
	
	@Autowired
	private SchedulerServiceImpl service;
	
	@Test
	public void testIsMediaLiked() throws IgnfinityException{
		Media media = new Media();		
		media.setCode("Bat0XHuFSfR");
		
		List<Media> medias = new ArrayList<>();
		medias.add(media);
		
//		service.scheduleLikeHashtags(medias, context);
	}
	
	@Before
	public void login() throws IgnfinityException {
		context = loginService.login("test10262017", "qwer1234");
	}

	@After
	public void logout() {
		try {
			loginService.logout(context);
		} catch (IgnfinityException e) {
		}
	}
	
}
