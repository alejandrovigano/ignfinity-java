package ar.com.vga.ignfinity.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.vga.ignfinity.core.domain.Like;
import ar.com.vga.ignfinity.core.endpoint.MediaEndpoint;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.endpoint.entities.media.Media;
import ar.com.vga.ignfinity.core.endpoint.entities.media.MediaDetailRoot;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;
import ar.com.vga.ignfinity.core.repository.LikeRepository;
import ar.com.vga.ignfinity.core.service.MediaService;

@Component
public class MediaServiceImpl implements MediaService {

	@Autowired
	private MediaEndpoint mediaEndpoint;

	@Autowired
	private LikeRepository likeRepository;

	@Override
	public boolean isMediaLiked(Media media, IgnfinityContext context) throws IgnfinityException {

		boolean isMediaLiked = false;

		// no esta en la db
		int count = likeRepository.countByMediaId(media.getId());
		if (count > 0) {
			isMediaLiked = true;
		}

		if (!isMediaLiked) {
			String code = media.getCodeOrShortCode();
			MediaDetailRoot detail = mediaEndpoint.mediaDetail(code, context);
			isMediaLiked = Boolean.valueOf(detail.getGraphql().getShortcodeMedia().getViewerHasLiked());
			if (isMediaLiked) {
				Like like = new Like();
				like.setMediaCode(detail.getGraphql().getShortcodeMedia().getCodeOrShortCode());
				like.setMediaId(detail.getGraphql().getShortcodeMedia().getId());
				likeRepository.save(like);
			}
		}

		return isMediaLiked;
	}

}
