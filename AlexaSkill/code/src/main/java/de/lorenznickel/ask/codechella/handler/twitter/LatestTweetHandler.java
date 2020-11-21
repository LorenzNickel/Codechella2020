package main.java.de.lorenznickel.ask.codechella.handler.twitter;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import main.java.de.lorenznickel.ask.codechella.JsonReader;
import main.java.de.lorenznickel.ask.codechella.MySQL;
import main.java.de.lorenznickel.ask.codechella.TwitterInformation;
import main.java.de.lorenznickel.ask.codechella.User;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class LatestTweetHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("lastTweetTimeline"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String token = input.getRequestEnvelope().getContext().getSystem().getUser().getAccessToken();
        if (token != null) {
            JsonReader helper = new JsonReader();
            User user = helper.getUserDataFromAmazon(token);
            MySQL mysql = new MySQL();
            if (!mysql.setUpTwitterCredentials(user)) {
                String speechText = "It seems like you are not properly authenticated with Twitter on our website.";
                return input.getResponseBuilder()
                    .withSpeech(speechText)
                    .withShouldEndSession(true)
                    .build();
            } else {
                String speechText = TwitterInformation.getLastTweet(user);
                return input.getResponseBuilder()
                    .withSpeech(speechText)
                    .withShouldEndSession(true)
                    .build();
            }
        } else {
            String speechText = "I need access to your e-mail address. Please give me this permission using the card I sent to your in the Alexa-App.";
            return input.getResponseBuilder()
                .withSpeech(speechText)
                .withLinkAccountCard()
                .build();
        }
    }
}
