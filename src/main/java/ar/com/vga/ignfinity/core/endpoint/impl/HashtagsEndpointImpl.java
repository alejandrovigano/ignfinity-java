package ar.com.vga.ignfinity.core.endpoint.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.vga.ignfinity.core.endpoint.HashtagsEndpoint;
import ar.com.vga.ignfinity.core.endpoint.IgnfinityEndpoint;
import ar.com.vga.ignfinity.core.endpoint.constants.IgnfinityURLS;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityPage;
import ar.com.vga.ignfinity.core.endpoint.entities.hashtags.HashtagsRoot;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

@Component
public class HashtagsEndpointImpl implements HashtagsEndpoint {

	@Autowired
	private IgnfinityEndpoint ignfinityEndpoint;

	@Override
	public IgnfinityPage<HashtagsRoot> mediaByHashtag(String tag, IgnfinityContext context) throws IgnfinityException {
		String url = String.format(IgnfinityURLS.URL_TAG, tag);
		return mediaByUrl(tag, context, url);
	}

	@Override
	public IgnfinityPage<HashtagsRoot> mediaByHashtag(String tag, String maxId, IgnfinityContext context)
			throws IgnfinityException {
		String url = ignfinityEndpoint.getNextPage(String.format(IgnfinityURLS.URL_TAG, tag), maxId);
		return mediaByUrl(tag, context, url);
	}

	private IgnfinityPage<HashtagsRoot> mediaByUrl(String tag, IgnfinityContext context, String url)
			throws IgnfinityException {
		HashtagsRoot hashtags = ignfinityEndpoint.get(url, context,
				HashtagsRoot.class);
		
		IgnfinityPage<HashtagsRoot> page = new IgnfinityPage<>();
		page.setBody(hashtags);
		page.setHasNextPage(hashtags.getTag().getMedia().getPageInfo().isHasNextPage());
		page.setEndCursor(hashtags.getTag().getMedia().getPageInfo().getEndCursor());
		page.setNext(() -> this.mediaByHashtag(tag, page.getEndCursor(), context));
		
		return page;
	}

}
