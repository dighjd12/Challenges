package tweets;

import java.util.ArrayList;
import java.util.List;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 * The Class TwitterPlugin.
 */
public class TwitterPlugin {

	/** The twitter stream. */
	private static TwitterStream twitterStream;
	
	/** The statuses. */
	private static List<Status> statuses = new ArrayList<Status>();
	
	/** The framework. */
	private FrameworkCore f;
	
	/**
	 * Instantiates a new twitter plugin.
	 *
	 * @param f the framework core
	 */
	public TwitterPlugin(FrameworkCore f){
		this.f = f;
	}
	
	/**
	 * Collect tweets.
	 *
	 * @throws TwitterException the twitter exception
	 */
	public void collectTweets() throws TwitterException {
		
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey("")
                .setOAuthConsumerSecret("")
                .setOAuthAccessToken("")
                .setOAuthAccessTokenSecret("");
		
        twitterStream = new TwitterStreamFactory(configurationBuilder.build()).getInstance();
		
		getStreams();
		
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	stopStreams();
		            }
		        }, 
		        5*60*1000
		);
	}
	
	/**
	 * On ending streams.
	 */
	public void onEndingStreams(){
		System.out.println(statuses.size() + "tweets collected!");
		f.doAnalysis(statuses);
	}
	
	/**
	 * Stop streams.
	 */
	public void stopStreams(){
		twitterStream.shutdown();
		System.out.println("stopped streaming!");
		onEndingStreams();
	}
	
	/**
	 * Collect tweets.
	 *
	 * @throws TwitterException the twitter exception
	 */
	public void getStreams() throws TwitterException {
		
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                 statuses.add(status);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTrackLimitationNotice(int arg0) {
				// TODO Auto-generated method stub
				
			}
        };
        twitterStream.addListener(listener);
        
        //filter query to only get tweets in the US in English
        FilterQuery tweetFilterQuery = new FilterQuery();
        tweetFilterQuery.track(new String[]{});
        tweetFilterQuery.locations(new double[][]{new double[]{-126.562500,30.448674},
                        new double[]{-61.171875,44.087585
                        }});
        tweetFilterQuery.language(new String[]{"en"});
        
        twitterStream.filter(tweetFilterQuery);
        
      //  twitterStream.sample();
    }
	
}
