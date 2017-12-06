package ar.com.vga.ignfinity.core.endpoint.entities.media;

public class MediaDetailRoot {

	private MediaDetailRoot.GraphQL graphql;

	public MediaDetailRoot.GraphQL getGraphql() {
		return graphql;
	}

	public void setGraphql(MediaDetailRoot.GraphQL graphql) {
		this.graphql = graphql;
	}

	public static class GraphQL {
		private MediaDetail shortcodeMedia;

		public MediaDetail getShortcodeMedia() {
			return shortcodeMedia;
		}

		public void setShortcodeMedia(MediaDetail shortcodeMedia) {
			this.shortcodeMedia = shortcodeMedia;
		}
	}
}
