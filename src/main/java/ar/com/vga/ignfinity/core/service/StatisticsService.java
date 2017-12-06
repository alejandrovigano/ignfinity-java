package ar.com.vga.ignfinity.core.service;

import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityPage;
import ar.com.vga.ignfinity.core.endpoint.entities.hashtags.HashtagsRoot;

public interface StatisticsService {

	void likeAllHastags(IgnfinityPage<HashtagsRoot> hashtagMedia);

}
