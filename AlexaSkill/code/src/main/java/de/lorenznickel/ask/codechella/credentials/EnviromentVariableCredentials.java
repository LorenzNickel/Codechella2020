package main.java.de.lorenznickel.ask.codechella.credentials;

public class HardcodedCredentials implements Credentials {

    @Override
    public String getSkillId() {
        return "amzn1.ask.skill.e7a151d5-019c-48a9-a873-b619e03799ae";
    }

    @Override
    public String getDatabaseURL() {
        return "jdbc:mysql://52.57.169.64/codechella";
    }

    @Override
    public String getUsername() {
        return "codechella";
    }

    @Override
    public String getPassword() {
        return "aM1GgPFzMJuMLoKL";
    }

    @Override
    public String getConsumerKey() {
        return "37Xxrj9VaGMdZdRaKRY97gigM";
    }

    @Override
    public String getConsumerSecret() {
        return "rGidM310GOzQjEpUgJUIxktV8fx2O61TR1LrN7wiutN0SvZqPi";
    }
}
