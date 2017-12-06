package ar.com.vga.ignfinity.core.endpoint.entities.hashtags;

import java.util.List;

import ar.com.vga.ignfinity.core.endpoint.entities.PageInfo;
import ar.com.vga.ignfinity.core.endpoint.entities.media.Media;

public class HashtagPage {

	private List<Media> nodes;
	private Long count;
	private PageInfo pageInfo;
	public List<Media> getNodes() {
		return nodes;
	}
	public void setNodes(List<Media> nodes) {
		this.nodes = nodes;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
	
}
