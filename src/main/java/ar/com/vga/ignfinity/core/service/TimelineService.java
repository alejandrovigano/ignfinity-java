package ar.com.vga.ignfinity.core.service;

import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

public interface TimelineService {

	void likeTimeline(IgnfinityContext context, Integer minsDelay) throws IgnfinityException;

}
