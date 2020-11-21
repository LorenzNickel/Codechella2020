package main.java.de.lorenznickel.ask.codechella;

public class User {

    private String user_id;
    private String name;
    private String email;
    private String accessToken;
    private String secretToken;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setSecretToken(String secretToken) {
        this.secretToken = secretToken;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getSecretToken() {
        return secretToken;
    }

    @Override
    public String toString() {
        return "User{" +
            "user_id='" + user_id + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", accessToken='" + accessToken + '\'' +
            ", secretToken='" + secretToken + '\'' +
            '}';
    }
}
