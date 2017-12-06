package ar.com.vga.ignfinity.core.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ar.com.vga.ignfinity.core.service.SchedulerService;

@Component
public class TaskRunner {

	@Autowired
	private SchedulerService schedulerService;
	
	@Scheduled(cron = "0 0/1 * * * ?")
	public void runTasks() {
		schedulerService.run(5);
	}

}
