package tweets;

import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * The Class FrameworkCore.
 */
public class FrameworkCore{
	
	/** The twitter plugin. */
	private TwitterPlugin tw;
	
	/** The analysis object. */
	private KeywordAnalysis analysis;
	
	/**
	 * Instantiates a new framework core.
	 */
	public FrameworkCore(){
		tw = new TwitterPlugin(this);
		analysis = new KeywordAnalysis();
	}
	
	/**
	 * Register listener.
	 *
	 * @param l the listener panel
	 */
	public void registerListener(MyPanel l){
		l.onRegister(this);
	}
	
	/**
	 * Do analysis.
	 *
	 * @param statuses the statuses
	 */
	public void doAnalysis(List<Status> statuses){
		analysis.analyzeData(new Data(statuses));
	}
	
	/**
	 * On start.
	 */
	public void onStart(){
		
		try {
			tw.collectTweets();
		} catch (TwitterException e) {
			System.out.println("error in collecting tweets!");
		}
	}
	
	
}
