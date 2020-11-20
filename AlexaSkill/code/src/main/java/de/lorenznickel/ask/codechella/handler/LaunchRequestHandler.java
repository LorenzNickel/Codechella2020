package main.java.de.lorenznickel.ask.codechella.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import main.java.de.lorenznickel.ask.codechella.JsonReader;
import main.java.de.lorenznickel.ask.codechella.MySQL;
import main.java.de.lorenznickel.ask.codechella.User;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String token = input.getRequestEnvelope().getContext().getSystem().getUser().getAccessToken();
        if (token != null) {
            JsonReader helper = new JsonReader();
            User user = helper.getUserDataFromAmazon(token);

            MySQL mysql = new MySQL();
            if (mysql.userExists(user)) {
                return input.getResponseBuilder()
                    .withSpeech("Hello " + user.getName() + ", how can I help you ?")
                    .build();
            } else {
                return input.getResponseBuilder()
                    .withSpeech("Please authenticate your Twitteraccount on our webpage.")
                    .withSimpleCard("Codechella 2020", "Please login with your Twitter account on our webpage.")
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
