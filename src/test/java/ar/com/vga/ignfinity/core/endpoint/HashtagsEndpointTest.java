package ar.com.vga.ignfinity.core.endpoint;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityPage;
import ar.com.vga.ignfinity.core.endpoint.entities.hashtags.HashtagsRoot;
import ar.com.vga.ignfinity.core.endpoint.entities.media.Media;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HashtagsEndpointTest {

	@Autowired
	private LoginEndpoint loginEndpoint;

	@Autowired
	private HashtagsEndpoint hashtags;

	@Autowired
	private LikeEndpoint like;

	private IgnfinityContext context;

	@Test
	public void testLogin() throws Exception {
		IgnfinityPage<HashtagsRoot> hashtag = hashtags.mediaByHashtag("landscape", context);

		int i = 0;
		List<Media> nodes = hashtag.getBody().getTag().getMedia().getNodes();	
		while (i < 5) {
			i++;
			nodes.stream().forEach(x -> {
				System.err.println(x.getCode());
			});
			hashtag =  hashtag.next();
			if(hashtag != null) {
				nodes = hashtag.getBody().getTag().getMedia().getNodes();
			}
		}
		

		assertNotNull(hashtag);
	}

	@Before
	public void login() throws IgnfinityException {
		context = loginEndpoint.login("test10262017", "qwer1234");
	}

	@After
	public void logout() throws IgnfinityException {
		loginEndpoint.logout(context);
	}

}
