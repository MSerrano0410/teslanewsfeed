package objects;

import java.net.URL;
import java.util.Vector;

/**
 * Provides object wrapper for RSS feed objects. 
 * @author Marvin R. Serrano
 * @since 03/21/2015
 * @version 1.0
 */
public class FeedObject {
	public FeedObject() {
		
	}
	
	public void setArticleUrl (String url){
		this.articleUrl = url;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setArticleLinks(Vector<URL> articleLinks) {
		this.articleLinks = articleLinks;
	}
	
	public String getArticleUrl() {
		return this.articleUrl;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Vector<URL> getArticleLinks() {
		return this.articleLinks;
	}
	
	private String articleUrl;
	private String date;
	private String title;
	private String description;
	private Vector<URL> articleLinks;
}
