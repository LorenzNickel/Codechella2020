package main.java.de.lorenznickel.ask.codechella;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Arrays;

public class TwitterInformation {

    public static String getLastTweet(final User user) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey(StreamHandler.getCredentials().getConsumerKey())
            .setOAuthConsumerSecret(StreamHandler.getCredentials().getConsumerSecret())
            .setOAuthAccessToken(user.getAccessToken())
            .setOAuthAccessTokenSecret(user.getSecretToken())
            .setTweetModeExtended(true);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try {
            final ResponseList<Status> homeTimeline = twitter.getHomeTimeline();
            homeTimeline.removeIf(Status::isRetweet);
            if (homeTimeline.size() == 0) {
                return "No Tweets found. Are you following some people?";
            } else {
                final Status status = homeTimeline.get(0);
                String text = status.getText();
                Arrays.stream(status.getURLEntities()).forEach(e -> {
                    text.replace(e.getExpandedURL(), "link");
                });
                Arrays.stream(status.getMediaEntities()).forEach(e -> {
                    text.replace(e.getExpandedURL(), "link");
                });
                return status.getUser().getScreenName() + " is tweeting: " + text;
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return "An internal error occurred from the Twitter API.";
    }
}
