package main.java.de.lorenznickel.ask.codechella;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterInformation {

    public static String getLastTweet(final User user) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey(StreamHandler.getCredentials().getConsumerKey())
            .setOAuthConsumerSecret(StreamHandler.getCredentials().getConsumerSecret())
            .setOAuthAccessToken(user.getAccessToken())
            .setOAuthAccessTokenSecret(user.getSecretToken());
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try {
            final ResponseList<Status> homeTimeline = twitter.getHomeTimeline();
            if(homeTimeline.size() == 0) {
                return "No Tweets found. Are you following some people?";
            } else {
                return homeTimeline.get(0).getText();
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return "An internal error occured from the Twitter API.";
    }
}
