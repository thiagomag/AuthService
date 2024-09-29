package br.com.thiagomagdalena.authservice.service;

import br.com.thiagomagdalena.authservice.controller.dto.LoginRequest;
import br.com.thiagomagdalena.authservice.repository.UserRepository;
import br.com.thiagomagdalena.authservice.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Mono<String> login(LoginRequest loginRequest) {
        final var username = loginRequest.getUsername();
        final var password = loginRequest.getPassword();
        return userRepository.findByUsername(username)
                .flatMap(user -> {
                    if (passwordEncoder.matches(password, user.getPassword())) {
                        return Mono.just(jwtUtil.generateToken(username));
                    } else {
                        return Mono.error(new RuntimeException("Invalid credentials"));
                    }
                });
    }
}
