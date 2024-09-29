package br.com.thiagomagdalena.authservice.usecase.impl;

import br.com.thiagomagdalena.authservice.controller.dto.UserRequest;
import br.com.thiagomagdalena.authservice.model.User;
import br.com.thiagomagdalena.authservice.repository.UserRepository;
import br.com.thiagomagdalena.authservice.usecase.CreateUserUseCase;
import br.com.thiagomagdalena.authservice.usecase.adapter.UserAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;
    private final UserAdapter userAdpter;
    private final BCryptPasswordEncoder passwordEncoder;

    public CreateUserUseCaseImpl(UserRepository userRepository1,
                                 UserAdapter userAdpter) {
        this.userRepository = userRepository1;
        this.userAdpter = userAdpter;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Mono<User> execute(UserRequest userRequest) {
        final var user = userAdpter.adapt(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
