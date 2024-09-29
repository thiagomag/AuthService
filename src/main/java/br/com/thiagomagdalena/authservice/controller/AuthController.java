package br.com.thiagomagdalena.authservice.controller;

import br.com.thiagomagdalena.authservice.controller.dto.LoginRequest;
import br.com.thiagomagdalena.authservice.controller.dto.UserRequest;
import br.com.thiagomagdalena.authservice.service.AuthService;
import br.com.thiagomagdalena.authservice.usecase.CreateUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final CreateUserUseCase createUserUseCase;

    @PostMapping("/login")
    public Mono<ResponseEntity<String>> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest)
                .map(token -> ResponseEntity.ok("Bearer " + token))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body(e.getMessage())));
    }

    @PostMapping("/signup")
    public Mono<ResponseEntity<String>> signup(@RequestBody @Valid UserRequest userRequest) {
        return createUserUseCase.execute(userRequest)
                .map(u -> ResponseEntity.ok("User created successfully"))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body(e.getMessage())));
    }
}
