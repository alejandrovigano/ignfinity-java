package ar.com.vga.ignfinity.core.endpoint.entities.timeline;

import java.util.List;

import ar.com.vga.ignfinity.core.endpoint.entities.PageInfo;

public class Timeline {

	private PageInfo pageInfo;
	private List<TimelineEdge> edges;

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List<TimelineEdge> getEdges() {
		return edges;
	}

	public void setEdges(List<TimelineEdge> edges) {
		this.edges = edges;
	}

}
