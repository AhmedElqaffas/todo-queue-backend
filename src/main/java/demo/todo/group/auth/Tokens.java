package demo.todo.group.auth;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Tokens {
    @JsonProperty("id_token")
    private final String idToken;
    @JsonProperty("access_token")
    private final String accessToken;

    @JsonProperty("refresh_token")
    private final String refreshToken;

    public Tokens(String idToken, String accessToken, String refreshToken){
        this.idToken = idToken;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
