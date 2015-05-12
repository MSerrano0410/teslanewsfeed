import static org.junit.Assert.*;

import helpers.RSSParseHelpers;

import java.net.URI;
import java.net.URL;

import org.junit.Test;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;


public class RSSFeedParseTests {

	@Test
	public void testRSSAccess() {
		try {
			URI uri = new URL("http://feeds.washingtonpost.com/rss/rss_federal-eye").toURI();
			SyndFeedInput i = new SyndFeedInput();
			SyndFeed feed = i.build(new XmlReader(uri.toURL()));
			SyndEntryImpl l = (SyndEntryImpl)feed.getEntries().get(0);
			assertNotNull(feed);
			assertNotNull(l);
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testHTMLMarkupRemove() {
		try {
			URI uri = new URL("http://feeds.washingtonpost.com/rss/rss_federal-eye").toURI();
			SyndFeedInput i = new SyndFeedInput();
			SyndFeed feed = i.build(new XmlReader(uri.toURL()));
			SyndEntryImpl l = (SyndEntryImpl)feed.getEntries().get(0);
			RSSParseHelpers r = new RSSParseHelpers(l);
			assertNotNull(r.FeedObjWithoutHTML());
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
