package demo.todo.group.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AuthService {

    @Value("${spring.security.cognito.token-url}")
    private String url;
    @Value("${spring.security.cognito.redirect-url}")
    private String redirectUrl;
    @Value("${spring.security.cognito.client-id}")
    private String clientId;
    @Value("${spring.security.cognito.client-secret}")
    private String clientSecret;
    public Tokens getTokens(String code) {
        WebClient webClient = WebClient.builder().baseUrl(url).build();
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("code", code)
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("redirect_uri", redirectUrl)
                        .queryParam("client_id", clientId)
                        .queryParam("client_secret", clientSecret)
                        .queryParam("scope", "aws.cognito.signin.user.admin")
                        .build())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .retrieve()
                .bodyToMono(Tokens.class)
                .block();
    }
}
