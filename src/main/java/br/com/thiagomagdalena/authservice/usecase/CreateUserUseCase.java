package br.com.thiagomagdalena.authservice.usecase;

import br.com.thiagomagdalena.authservice.controller.dto.UserRequest;
import br.com.thiagomagdalena.authservice.model.User;
import reactor.core.publisher.Mono;

public interface CreateUserUseCase extends UseCase<UserRequest, Mono<User>> {
}
