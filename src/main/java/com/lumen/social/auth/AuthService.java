package com.lumen.social.auth;

import com.lumen.social.user.User;
import com.lumen.social.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    // Injestão de dependecia automatica

    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());

        //Nunca salva assim, do jeito que veio (ERRO PROPOSITAL)
        user.setPasswordHash(request.password());

        return userRepository.save(user);
    }
}
