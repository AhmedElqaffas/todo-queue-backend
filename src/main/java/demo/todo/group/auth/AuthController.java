package demo.todo.group.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/token")
    public ResponseEntity<Tokens> getJwtToken(@RequestParam String code){
        return ResponseEntity.ok(authService.getTokens(code));
    }

    @ExceptionHandler(WebClientResponseException.BadRequest.class)
    public ResponseEntity<String> badCognitoRequest(WebClientResponseException.BadRequest ex, WebRequest request) {
        return ResponseEntity.badRequest().body("Couldn't get tokens for the given code.");
    }
}
