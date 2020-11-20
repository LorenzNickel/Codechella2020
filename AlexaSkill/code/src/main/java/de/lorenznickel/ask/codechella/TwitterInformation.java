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
            homeTimeline.removeIf(s -> s.getQuotedStatusId() != -1);
            if (homeTimeline.size() == 0) {
                return "No Tweets found. Are you following some people?";
            } else {
                final Status status = homeTimeline.get(0);
                final String text = sanitizeUrls(status);
                return status.getUser().getScreenName() + " is tweeting: " + text;
            }
        } catch (TwitterException e) {
            e.printStackTrace();
            return "Sorry I got an error from Twitter: " + e.getErrorMessage();
        }
    }

    public static String getSearchResults(final User user, final String query) {
        Twitter twitter = createTwitterInstance(user);
        try {
            final QueryResult search = twitter.search(new Query(query));
            final List<Status> tweets = search.getTweets();
            tweets.removeIf(Status::isRetweet);
            tweets.removeIf(s -> s.getQuotedStatusId() != -1);
            if (tweets.size() == 0) {
                return "No Tweets found.";
            } else {
                final Status status = tweets.get(0);
                final String text = sanitizeUrls(status);
                return status.getUser().getScreenName() + " is tweeting: " + text;
            }
        } catch (TwitterException e) {
            e.printStackTrace();
            return "Sorry I got an error from Twitter: " + e.getErrorMessage();
        }
    }

    public static String getLastTweetFrom(final User user, String query) {
        Twitter twitter = createTwitterInstance(user);
        query = query.replace(" ", "");
        try {
            final twitter4j.User targetUser = twitter.showUser(query);
            final ResponseList<Status> userTimeline = twitter.getUserTimeline(targetUser.getId());
            userTimeline.removeIf(Status::isRetweet);
            userTimeline.removeIf(s -> s.getQuotedStatusId() != -1);
            if (userTimeline.size() == 0) {
                return "No Tweets found. Has this person tweeted yet?";
            } else {
                final Status status = userTimeline.get(0);
                final String text = sanitizeUrls(status);
                return "The last tweet from " + status.getUser().getScreenName() + " is: " + text;
            }
        } catch (TwitterException e) {
            e.printStackTrace();
            return "Sorry I got an error from Twitter: " + e.getErrorMessage();
        }
    }

    public static String sanitizeUrls(final Status status) {
        String text = status.getText();
        for (URLEntity urlEntity : status.getURLEntities()) {
            text = text.replace(urlEntity.getExpandedURL(), "link");
            text = text.replace(urlEntity.getURL(), "link");
        }
        for (URLEntity urlEntity : status.getMediaEntities()) {
            text = text.replace(urlEntity.getExpandedURL(), "link");
            text = text.replace(urlEntity.getURL(), "link");
        }
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
