package main.java.de.lorenznickel.ask.codechella.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class FallbackIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.FallbackIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Sorry, I didn't get that. Please try again.";
        return input.getResponseBuilder()
            .withSpeech(speechText)
            .withSimpleCard("Codechella 2020", speechText)
            .withReprompt("Please try again.")
            .build();
    }

}
