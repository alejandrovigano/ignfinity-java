package ar.com.vga.ignfinity.core.endpoint;

import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.endpoint.entities.media.MediaDetailRoot;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

public interface MediaEndpoint {

	MediaDetailRoot mediaDetail(String id, IgnfinityContext context) throws IgnfinityException;

}
