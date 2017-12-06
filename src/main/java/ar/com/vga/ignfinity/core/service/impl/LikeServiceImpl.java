package ar.com.vga.ignfinity.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.vga.ignfinity.core.domain.Like;
import ar.com.vga.ignfinity.core.endpoint.FollowEndpoint;
import ar.com.vga.ignfinity.core.endpoint.LikeEndpoint;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.endpoint.entities.media.Media;
import ar.com.vga.ignfinity.core.endpoint.entities.timeline.TimelineMedia;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;
import ar.com.vga.ignfinity.core.repository.LikeRepository;
import ar.com.vga.ignfinity.core.service.LikeService;
import ar.com.vga.ignfinity.core.service.MediaService;

@Component
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeEndpoint likeEndpoint;

	@Autowired
	private MediaService mediaService;

	@Autowired
	private LikeRepository likeRepository;

	@Autowired
	private FollowEndpoint endpoint;
	
	@Override
	public void like(Media media, IgnfinityContext context) throws IgnfinityException {

		boolean isMediaLiked = mediaService.isMediaLiked(media, context);

		if (!isMediaLiked) {
			Like like = new Like();
			like.setMediaCode(media.getCodeOrShortCode());
			like.setMediaId(media.getId());

			if(!media.getOwner().getUsername().equals("burned.withdesire")) {
				likeEndpoint.like(media.getId(), context);
	
				likeRepository.save(like);
				
				if(! (media instanceof TimelineMedia)) {				
					String owner = media.getOwner().getId();
					endpoint.follow(owner, context);
				}
			}
		}
	}

}
