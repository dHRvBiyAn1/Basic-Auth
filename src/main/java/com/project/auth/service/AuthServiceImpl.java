package com.project.auth.service;

import com.project.auth.dtos.LoginRequest;
import com.project.auth.dtos.LoginResponse;
import com.project.auth.dtos.RegisterRequest;
import com.project.auth.dtos.RegisterResponse;
import com.project.auth.entity.User;
import com.project.auth.exception.EmailAlreadyExistsException;
import com.project.auth.exception.InvalidUsernamePasswordException;
import com.project.auth.exception.UsernameAlreadyExistsException;
import com.project.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return new RegisterResponse(
                user.getUsername(),
                user.getEmail()
        );
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidUsernamePasswordException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidUsernamePasswordException("Invalid username or password");
        }

        return new LoginResponse(
                user.getUsername(),
                user.getEmail()
        );
    }
}
