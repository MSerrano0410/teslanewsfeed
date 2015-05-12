package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Vector;

import helpers.RSSParseHelpers;
import objects.FeedObject;

import com.sun.syndication.feed.synd.SyndEntryImpl;

public class RSSTextOutput {
	public RSSTextOutput(String directory, SyndEntryImpl rssEntry) {
		this.directory = directory;
		this.h = new RSSParseHelpers(rssEntry);
		this.feedObj = h.FeedObjWithoutHTML();
	}
	
	/**
	 * Writes RSS output to text file; creates it if it doesn't exist.
	 */
	public void writeToTxt() {
		String output = setUpOutput();
		
		String fileName = feedObj.getDate() + "-" + feedObj.getTitle() + ".txt";
		File outputFile = new File(directory + File.separator + fileName);			
		try {
			PrintWriter out = new PrintWriter(outputFile);
			out.println(output);
			System.out.println("RSS entry from " + feedObj.getArticleUrl() + " parsed to " + outputFile.getAbsolutePath());
			out.close();
		}
			
		catch(FileNotFoundException f) {
			System.out.println("Error: file not found at " + outputFile.getAbsolutePath() + ". Check your permissions.");
		}
			
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets up text output with spaces.
	 * @return Formatted string, with spaces for output.
	 */
	private String setUpOutput() {
		String output = "URL: " + feedObj.getArticleUrl() + "\r\n\r\n" + 
				"Title: " + feedObj.getTitle() + "\r\n\r\n" + 
				"Article Text: " + feedObj.getDescription() + "\r\n\r\n" +
				"Links: ";
		
		Vector<URL> artLinks = feedObj.getArticleLinks();
		for(int i = 0; i < artLinks.size(); i++) {
			output += artLinks.get(i);
			if(i != artLinks.size() - 1)
				output += ", ";
		}
		
		return output;
	}
	
	
	String directory;
	RSSParseHelpers h;
	FeedObject feedObj;
}
