package br.com.thiagomagdalena.authservice.usecase.adapter;

import br.com.thiagomagdalena.authservice.adapter.AbstractAdapter;
import br.com.thiagomagdalena.authservice.controller.dto.UserRequest;
import br.com.thiagomagdalena.authservice.model.User;
import br.com.thiagomagdalena.authservice.utils.JsonUtils;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter extends AbstractAdapter<UserRequest, User> {

    public UserAdapter(JsonUtils jsonUtils) {
        super(User.class, jsonUtils);
    }
}
