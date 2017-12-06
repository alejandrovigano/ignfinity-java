package ar.com.vga.ignfinity.core.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.vga.ignfinity.core.endpoint.TimelineEndpoint;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.endpoint.entities.media.Media;
import ar.com.vga.ignfinity.core.endpoint.entities.timeline.TimelineRoot;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;
import ar.com.vga.ignfinity.core.service.SchedulerService;
import ar.com.vga.ignfinity.core.service.TimelineService;

@Component
public class TimelineServiceImpl implements TimelineService{

	@Autowired
	private TimelineEndpoint endpoint;

	@Autowired
	private SchedulerService schedulerService;

	@Override
	public void likeTimeline(IgnfinityContext context, Integer minsDelay) throws IgnfinityException {
		TimelineRoot timelineRoot = endpoint.timeline(context);

		List<Media> media = timelineRoot.getGraphql().getUser().getEdgeWebFeedTimeline().getEdges().stream()
				.map(x -> x.getNode()).limit(5).collect(Collectors.toList());
		schedulerService.scheduleLikeMedia(media, context, minsDelay);
	}
}
