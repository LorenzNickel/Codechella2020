package main.java.de.lorenznickel.ask.codechella;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Arrays;
import java.util.List;

public class TwitterInformation {

    public static String getLastTweet(final User user) {
        Twitter twitter = createTwitterInstance(user);
        try {
            final ResponseList<Status> homeTimeline = twitter.getHomeTimeline();
            homeTimeline.removeIf(Status::isRetweet);
            if (homeTimeline.size() == 0) {
                return "No Tweets found. Are you following some people?";
            } else {
                final Status status = homeTimeline.get(0);
                final String text = sanitizeUrls(status);
                return status.getUser().getScreenName() + " is tweeting: " + text;
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return "An internal error occurred from the Twitter API.";
    }

    public static String getSearchResults(final User user, final String query) {
        Twitter twitter = createTwitterInstance(user);
        try {
            final QueryResult search = twitter.search(new Query(query));
            final List<Status> tweets = search.getTweets();
            tweets.removeIf(Status::isRetweet);
            if (tweets.size() == 0) {
                return "No Tweets found.";
            } else {
                final Status status = tweets.get(0);
                final String text = sanitizeUrls(status);
                return status.getUser().getScreenName() + " is tweeting: " + text;
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return "An internal error occurred from the Twitter API.";
    }

    public static String sanitizeUrls(final Status status) {
        String text = status.getText();
        Arrays.stream(status.getURLEntities()).forEach(e -> {
            text.replace(e.getExpandedURL(), "link");
        });
        Arrays.stream(status.getMediaEntities()).forEach(e -> {
            text.replace(e.getExpandedURL(), "link");
        });
        return text;
    }

    public static Twitter createTwitterInstance(final User user) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey(StreamHandler.getCredentials().getConsumerKey())
            .setOAuthConsumerSecret(StreamHandler.getCredentials().getConsumerSecret())
            .setOAuthAccessToken(user.getAccessToken())
            .setOAuthAccessTokenSecret(user.getSecretToken())
            .setTweetModeExtended(true);
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }

}
