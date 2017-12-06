package ar.com.vga.ignfinity.core.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.vga.ignfinity.core.domain.Ignfinity;
import ar.com.vga.ignfinity.core.domain.SchedulerConfig;
import ar.com.vga.ignfinity.core.domain.plan.HashtagPlan;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;
import ar.com.vga.ignfinity.core.repository.HashtagPlanRepository;
import ar.com.vga.ignfinity.core.repository.IgnfinityRepository;
import ar.com.vga.ignfinity.core.repository.SchedulerConfigRepository;
import ar.com.vga.ignfinity.core.service.ContextService;
import ar.com.vga.ignfinity.core.service.HashtagLikeService;
import ar.com.vga.ignfinity.core.service.HashtagPlanService;

@Component
public class HashtagPlanServiceImpl implements HashtagPlanService {

	@Autowired
	private HashtagPlanRepository hashtagPlanRepository;

	@Autowired
	private SchedulerConfigRepository schedulerConfigRepository;

	@Autowired
	private HashtagLikeService hashtagLikeService;

	@Autowired
	private ContextService contextService;

	@Autowired
	private IgnfinityRepository ignfinityRepository;

	@Override
	public void runPlan(Integer minsDelay) throws IgnfinityException {
		List<HashtagPlan> hashtags = hashtagPlanRepository.findAll();

		Integer likesPerHastag = calculateMediaPerHashtag(hashtags, minsDelay);

		Ignfinity ig = ignfinityRepository.findAll().get(0);
		IgnfinityContext context = contextService.findByUserId(ig.getUserId());

		Collections.shuffle(hashtags);
		for (HashtagPlan hashtagPlan : hashtags) {
			hashtagLikeService.searchAndLikeMediaByHashtag(hashtagPlan.getHashtag(), context, minsDelay,
					likesPerHastag);
		}
	}

	private Integer calculateMediaPerHashtag(List<HashtagPlan> hashtags, Integer minsDelay) {
		SchedulerConfig config = schedulerConfigRepository.findAll().get(0);

		Integer likesPerDay = config.getMaxLikesPerDay();
		Double likesPerHour = (double) likesPerDay / 24;
		Double likesPerMinute = (double) (likesPerHour / 60);
		Double likesPerHashtagPerMinute = likesPerMinute / hashtags.size();

		Integer likesPerMinutes = (int) ((likesPerHashtagPerMinute * minsDelay) + 1);

		return likesPerMinutes;
	}

}
