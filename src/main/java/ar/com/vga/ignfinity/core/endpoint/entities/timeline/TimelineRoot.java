package ar.com.vga.ignfinity.core.endpoint.entities.timeline;

public class TimelineRoot {

	private TimelineRoot.GraphQL graphql;

	public GraphQL getGraphql() {
		return graphql;
	}

	public void setGraphql(GraphQL graphql) {
		this.graphql = graphql;
	}

	public static class GraphQL {

		private TimelineUser user;

		public TimelineUser getUser() {
			return user;
		}

		public void setUser(TimelineUser user) {
			this.user = user;
		}
	}
}
