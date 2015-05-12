package main;

import helpers.RSSParseHelpers;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class MainFeed {
	public static void main(String[] args) {
		try {
			if(args.length == 0 || args[0].length() <= 0) {
				System.out.println("Arguments for TeslaNewsFeed.jar: URL, directory(optional).");
				System.exit(0);		
			}
			
			String dir = "";
			RSSParseHelpers h = new RSSParseHelpers();
			
			if(args.length > 1)
				dir = h.checkIfDirExists(args[1]);
			else
				dir = h.getDefaultDirectory();
					
			URI uri = new URL(args[0]).toURI();
			SyndFeedInput i = new SyndFeedInput();
			SyndFeed feed = i.build(new XmlReader(uri.toURL()));
			SyndEntryImpl l = (SyndEntryImpl)feed.getEntries().get(0);
			RSSTextOutput o = new RSSTextOutput(dir, l);
			o.writeToTxt();
		}
		
		catch(MalformedURLException m) {
			System.out.println("Invalid URL. Make sure that your URL begins with http://.");
			System.exit(0);
		}
		
		catch(URISyntaxException u) {
			//NOTE: This is necessary because URL object accepts anything that starts with http.
			System.out.println("Could not convert URL to URI. URL should be in format http://somewebsite.com.");
			System.exit(0);
		} 
		
		catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		} 
	}
}
