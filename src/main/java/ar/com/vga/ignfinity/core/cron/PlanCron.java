package ar.com.vga.ignfinity.core.cron;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import ar.com.vga.ignfinity.core.domain.SchedulerConfig;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;
import ar.com.vga.ignfinity.core.repository.SchedulerConfigRepository;
import ar.com.vga.ignfinity.core.service.HashtagPlanService;
import ar.com.vga.ignfinity.core.service.LoginService;
import ar.com.vga.ignfinity.core.service.TimelinePlanService;

@Component
public class PlanCron {

	@Autowired
	private HashtagPlanService hashtagPlanService;
	@Autowired
	private TimelinePlanService timelinePlanService;
	@Autowired
	private SchedulerConfigRepository schedulerConfigRepository;
	@Autowired
	private TaskScheduler taskscheduler;
	@Autowired
	private LoginService loginService;

	@EventListener(ApplicationReadyEvent.class)
	public void runTasks() {
		login();
		run();
	}

	private void run() {
		SchedulerConfig config = schedulerConfigRepository.findAll().get(0);
		Integer minsDelay = randomBetween(config.getMinMinsDelayLikes(), config.getMaxMinsDelayLikes());

		try {
			hashtagPlanService.runPlan(minsDelay);
			timelinePlanService.runPlan(minsDelay);
		} catch (IgnfinityException e) {
			System.err.println("Error c" + e.getMessage());
		}
		taskscheduler.schedule(this::runTasks, addMinutes(new Date(), minsDelay));

	}

	private void login() {
		try {
			IgnfinityContext context = loginService.login("alejandro.vigano.ph", "NOkia3220");
		} catch (IgnfinityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Integer randomBetween(Integer min, Integer max) {
		return (min + new Random(new Date().getTime()).nextInt(max - min));
	}

	private Date addMinutes(Date baseStartTime, Integer minsDelay) {
		Calendar date = Calendar.getInstance();
		long t = date.getTimeInMillis();
		return new Date(t + (minsDelay * 1000 * 60));
	}

}
