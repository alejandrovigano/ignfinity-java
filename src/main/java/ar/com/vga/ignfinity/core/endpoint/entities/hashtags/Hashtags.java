package ar.com.vga.ignfinity.core.endpoint.entities.hashtags;

public class Hashtags {

	private String name;
	private HashtagPage media;
	private HashtagPage topPosts;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashtagPage getMedia() {
		return media;
	}

	public void setMedia(HashtagPage media) {
		this.media = media;
	}

	public HashtagPage getTopPosts() {
		return topPosts;
	}

	public void setTopPosts(HashtagPage topPosts) {
		this.topPosts = topPosts;
	}

}
