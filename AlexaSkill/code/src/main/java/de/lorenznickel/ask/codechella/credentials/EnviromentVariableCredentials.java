package main.java.de.lorenznickel.ask.codechella.credentials;

public class EnviromentVariableCredentials implements Credentials {

    @Override
    public String getSkillId() {
        return System.getenv("skillId");
    }

    @Override
    public String getDatabaseURL() {
        return System.getenv("databaseUrl");
    }

    @Override
    public String getUsername() {
        return System.getenv("username");
    }

    @Override
    public String getPassword() {
        return System.getenv("password");
    }

    @Override
    public String getConsumerKey() {
        return System.getenv("consumerKey");
    }

    @Override
    public String getConsumerSecret() {
        return System.getenv("consumerSecret");
    }
}
