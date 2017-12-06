package ar.com.vga.ignfinity.core.endpoint;

import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityPage;
import ar.com.vga.ignfinity.core.endpoint.entities.hashtags.HashtagsRoot;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

public interface HashtagsEndpoint {
	
	IgnfinityPage<HashtagsRoot> mediaByHashtag(String tag, IgnfinityContext contexto) throws IgnfinityException;
	IgnfinityPage<HashtagsRoot> mediaByHashtag(String tag, String maxId, IgnfinityContext contexto) throws IgnfinityException;

}
