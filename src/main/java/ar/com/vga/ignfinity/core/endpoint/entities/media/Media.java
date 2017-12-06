package ar.com.vga.ignfinity.core.endpoint.entities.media;

import ar.com.vga.ignfinity.core.endpoint.entities.User;

public class Media {

	private String id;
	private String shortcode;
	private String code;
	private User owner;
	private String viewerHasLiked;
	private String caption;
	private String displaySrc;
	private Long date;

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getDisplaySrc() {
		return displaySrc;
	}

	public void setDisplaySrc(String displaySrc) {
		this.displaySrc = displaySrc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShortcode() {
		return shortcode;
	}

	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}

	public String getViewerHasLiked() {
		return viewerHasLiked;
	}

	public void setViewerHasLiked(String viewerHasLiked) {
		this.viewerHasLiked = viewerHasLiked;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodeOrShortCode() {
		return this.getCode() != null ? this.getCode() : this.getShortcode();
	}

}
