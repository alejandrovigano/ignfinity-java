package ar.com.vga.ignfinity.core.service;

import java.util.List;

import ar.com.vga.ignfinity.core.domain.ScheduledTask;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.endpoint.entities.media.Media;

public interface SchedulerService {

	void run(int range);

	List<ScheduledTask> scheduleLikeMedia(List<Media> media, IgnfinityContext context, Integer minsDelay);

}
