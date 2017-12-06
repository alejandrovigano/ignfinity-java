package ar.com.vga.ignfinity.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.vga.ignfinity.core.domain.Ignfinity;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;
import ar.com.vga.ignfinity.core.repository.IgnfinityRepository;
import ar.com.vga.ignfinity.core.service.ContextService;
import ar.com.vga.ignfinity.core.service.TimelinePlanService;
import ar.com.vga.ignfinity.core.service.TimelineService;

@Component
public class TimelinePlanServiceImpl implements TimelinePlanService {

	@Autowired
	private TimelineService timelineService;

	@Autowired
	private ContextService contextService;

	@Autowired
	private IgnfinityRepository ignfinityRepository;

	@Override
	public void runPlan(Integer minsDelay) throws IgnfinityException {

		Ignfinity ig = ignfinityRepository.findAll().get(0);
		IgnfinityContext context = contextService.findByUserId(ig.getUserId());

		timelineService.likeTimeline(context, minsDelay);
	}

}
