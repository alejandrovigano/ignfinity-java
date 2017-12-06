package ar.com.vga.ignfinity.core.service;

import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.endpoint.entities.media.Media;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

public interface LikeService {

	void like(Media media, IgnfinityContext context) throws IgnfinityException;
	
}
