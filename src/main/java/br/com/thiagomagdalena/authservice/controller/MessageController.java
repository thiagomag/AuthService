package br.com.thiagomagdalena.authservice.controller;

import br.com.thiagomagdalena.authservice.service.RabbitMQSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/send")
public class MessageController {

    private final RabbitMQSender rabbitMQSender;

    public MessageController(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    @PostMapping()
    public Mono<String> sendMessage(@RequestBody String message) {
        return Mono.fromRunnable(() -> rabbitMQSender.send(message))
                .then(Mono.just("Mensagem enviada: " + message));
    }
}