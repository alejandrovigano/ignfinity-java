package ar.com.vga.ignfinity.core.service;

import java.util.List;

import ar.com.vga.ignfinity.core.domain.ScheduledTask;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

public interface HashtagLikeService {

	List<ScheduledTask> searchAndLikeMediaByHashtag(String hashtag, IgnfinityContext context, Integer minsDelay, Integer likesPerHastag)
			throws IgnfinityException;

}
