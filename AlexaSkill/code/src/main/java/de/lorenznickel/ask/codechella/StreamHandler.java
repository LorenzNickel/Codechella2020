package main.java.de.lorenznickel.ask.codechella;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import main.java.de.lorenznickel.ask.codechella.credentials.Credentials;
import main.java.de.lorenznickel.ask.codechella.credentials.EnviromentVariableCredentials;
import main.java.de.lorenznickel.ask.codechella.handler.*;
import main.java.de.lorenznickel.ask.codechella.handler.twitter.LatestTweetHandler;
import main.java.de.lorenznickel.ask.codechella.handler.twitter.SearchTweetHandler;

public class StreamHandler extends SkillStreamHandler {

    private static final Credentials credentials = new EnviromentVariableCredentials();

    private static Skill getSkill() {
        return Skills.standard()
            .addRequestHandlers(
                new CancelandStopIntentHandler(),
                new FallbackIntentHandler(),
                new HelpIntentHandler(),
                new LaunchRequestHandler(),
                new SessionEndedRequestHandler(),
                new LatestTweetHandler(),
                new SearchTweetHandler()
            )
            .withSkillId(credentials.getSkillId())
            .build();
    }

    public StreamHandler() {
        super(getSkill());
    }

    public static Credentials getCredentials() {
        return credentials;
    }

}
