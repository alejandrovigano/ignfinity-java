package ar.com.vga.ignfinity.core.service.impl;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.vga.ignfinity.core.domain.ScheduledTask;
import ar.com.vga.ignfinity.core.endpoint.HashtagsEndpoint;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityPage;
import ar.com.vga.ignfinity.core.endpoint.entities.hashtags.HashtagPage;
import ar.com.vga.ignfinity.core.endpoint.entities.hashtags.HashtagsRoot;
import ar.com.vga.ignfinity.core.endpoint.entities.media.Media;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;
import ar.com.vga.ignfinity.core.service.Contador;
import ar.com.vga.ignfinity.core.service.HashtagLikeService;
import ar.com.vga.ignfinity.core.service.MediaService;
import ar.com.vga.ignfinity.core.service.SchedulerService;
import ar.com.vga.ignfinity.core.service.StatisticsService;


public class ProfileLikeFollowServiceImpl implements HashtagLikeService {

	@Autowired
	private HashtagsEndpoint hashtags;
	@Autowired
	private StatisticsService statsService;
	@Autowired
	private SchedulerService schedulerService;
	@Autowired
	private MediaService mediaService;

	private Map<String, List<Media>> pendingMedia = new HashMap<>();
	private Map<String, IgnfinityPage<HashtagsRoot>> lastPage = new HashMap<>();
	private Map<String, Date> lastUpdate = new HashMap<>();

	@Override
	public List<ScheduledTask> searchAndLikeMediaByHashtag(String hashtag, IgnfinityContext context, Integer minsDelay,
			Integer likesPerHastag) throws IgnfinityException {

		List<Media> pending = pendingMedia.get(hashtag);
		if (pending == null) {
			pending = new LinkedList<>();
			pendingMedia.put(hashtag, pending);
		}
		if (!(pending.size() > likesPerHastag)) {
			findMediaByApi(hashtag, context, likesPerHastag, pending);
		}

		// buscar hashtag
		List<Media> allMedia = pending.stream().limit(likesPerHastag).collect(Collectors.toList());
		pending.removeAll(allMedia);

		return schedulerService.scheduleLikeMedia(allMedia, context, minsDelay);
	}

	private List<Media> findMediaByApi(String hashtag, IgnfinityContext context, Integer likesPerHastag,
			List<Media> pending) throws IgnfinityException {

		List<Media> allMedia = new ArrayList<>();

		// statis
		IgnfinityPage<HashtagsRoot> hashtagMedia = lastPage.get(hashtag);
		if (lastPage.get(hashtag) != null || refrescar(hashtag)) {
			hashtagMedia = hashtagMedia.next();
		} else {
			update(hashtag);
			hashtagMedia = hashtags.mediaByHashtag(hashtag, context);
		}

		statsService.likeAllHastags(hashtagMedia);
		lastPage.put(hashtag, hashtagMedia);

		// primer pagina
		HashtagPage page = hashtagMedia.getBody().getTag().getMedia();

		allMedia.addAll(page.getNodes());

		// siguientes paginas
		while (allMedia.size() < 10 && hashtagMedia.getHasNextPage()) {
			hashtagMedia = hashtagMedia.next();
			lastPage.put(hashtag, hashtagMedia);

			page = hashtagMedia.getBody().getTag().getMedia();
			allMedia.addAll(page.getNodes());
		}

		Contador count = new Contador();
		// filtro los likeados
		pending.addAll(allMedia);
		allMedia = allMedia.stream().filter(x -> {
			try {
				if (mediaService.isMediaLiked(x, context)) {
					pending.remove(x);
					return false;
				}
				count.plus();
				return true;
			} catch (IgnfinityException e) {
				return false;
			}
		}).collect(Collectors.toList());

		// limito
		allMedia = allMedia.stream().limit(likesPerHastag).collect(Collectors.toList());
		pending.removeAll(allMedia);

		return allMedia;
	}

	private void update(String hashtag) {
		lastUpdate.put(hashtag, new Date());	
	}

	private boolean refrescar(String hashtag) {
		Date lastUp = lastUpdate.get(hashtag);
		if(lastUp == null) {
			lastUp = new Date();
			lastUpdate.put(hashtag, lastUp);
		}
		
		long hours = ChronoUnit.HOURS.between(lastUp.toInstant(), new Date().toInstant());
		if(hours > 0) {
			return true;
		}
		return false;
	}

	private void fillAllMedia(List<Media> allMedia) {
		// TODO Auto-generated method stub

	}

}
