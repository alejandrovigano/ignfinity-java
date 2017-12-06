package ar.com.vga.ignfinity.core.service;

import java.util.Calendar;
import java.util.Date;

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
import ar.com.vga.ignfinity.core.service.task.LikeMediaTask;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {

	@Autowired
	private LoginService loginService;

	private IgnfinityContext context;

	@Autowired
	private TaskService service;

	@Test
	public void testIsMediaLiked() throws IgnfinityException, InterruptedException {
		Media media = new Media();
		media.setCode("BawPAUOD5u5");

		LikeMediaTask task = new LikeMediaTask(media);
		

		Calendar date = Calendar.getInstance();
		long t= date.getTimeInMillis();
		Date afterAddingTenMins=new Date(t + (10 * 60000));
		
		task.setStartTime(afterAddingTenMins);
		task.setUserid(context.getUser().getId());

		task.accept(service);
		
		wait();
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
