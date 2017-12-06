package ar.com.vga.ignfinity.core.endpoint.entities.timeline;

import ar.com.vga.ignfinity.core.endpoint.entities.User;

public class TimelineUser extends User{

	private Timeline edgeWebFeedTimeline;

	public Timeline getEdgeWebFeedTimeline() {
		return edgeWebFeedTimeline;
	}

	public void setEdgeWebFeedTimeline(Timeline edgeWebFeedTimeline) {
		this.edgeWebFeedTimeline = edgeWebFeedTimeline;
	}

}
