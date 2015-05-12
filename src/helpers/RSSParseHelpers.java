package helpers;

import objects.FeedObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sun.syndication.feed.synd.SyndEntryImpl;


/**
 * Helper class for parsing RSS feeds and elements.
 * @author Marvin R. Serrano
 * @since 03/21/2015
 * @version 1.0
 */
public class RSSParseHelpers {
	public RSSParseHelpers() {
		
	}
	
	public RSSParseHelpers(SyndEntryImpl rssEntry) {
		this.entry = rssEntry;
	}
	
	/**
	 * Parses out all links in the description tag of the RSS entry.
	 * @return - Vector of all HTML links.
	 */
	public Vector<URL> getArticleLinks() {
		Vector<URL> links = new Vector<URL>();
		String description = entry.getDescription().getValue();	
		Matcher urlRegex = Pattern.compile("http://[a-zA-Z0-9]+(\\.)[a-zA-Z]{3}[^\"|\']*")
				.matcher(description);
		while(urlRegex.find()) {
			try {
				links.add(new URL(urlRegex.group()));
			}
			
			catch(MalformedURLException m) {
				continue;//accounts for faulty URLs in RSS feed.
			}
		}
		
		return links;
	}
		
	/**
	 * Removes all HTML markup from item elements, and sets up FeedObject.
	 * @return FeedObject object, with HTML elements stripped and body links parsed out.
	 */
	public FeedObject FeedObjWithoutHTML() {
		FeedObject obj = new FeedObject();
		obj.setTitle(Jsoup.parse(entry.getTitle()).text());
		obj.setDescription(Jsoup.parse(entry.getDescription().getValue()).text());
		obj.setDate(formatObjDate(entry.getPublishedDate()));
		obj.setArticleUrl(entry.getLink());
		obj.setArticleLinks(getArticleLinks());
		return obj;
	}
	
	/**
	 * Gets object date and returns yyyymmdd-format date
	 * @return - date, in yyyymmdd format.
	 */
	public String formatObjDate(Date date) {
		try {
			DateFormat formatTo = new SimpleDateFormat("yyyyMMdd", Locale.US);
			return formatTo.format(date);
		} 
		
		catch (Exception e) {
			System.out.println(e.getMessage());
			return date.toString();
		}
	}
	
	/**
	 * Checks if directory exists. If not, defaults to default working directory.
	 * @param dir
	 * @return
	 */
	public String checkIfDirExists(String dir) {
		try {
			File f = new File(dir);
			String path = f.getCanonicalPath();
			System.out.println("Creating output file to: " + path + ".");
			return path;
		}
		
		catch(Exception i) {
			return getDefaultDirectory();
		}
	}
	/**
	 * Gets default working directory for user's system.
	 * @return - default working directory (usually user's home directory)
	 */
	public String getDefaultDirectory() {
		System.out.println("No directory listed. Creating default directory...");
		Path currentRelativePath = Paths.get("");
		return currentRelativePath.toAbsolutePath().toString();
	}
	
	
	private SyndEntryImpl entry;
}
