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
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        // Check if username already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        // Create and save user
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        return new RegisterResponse(
                user.getUsername(),
                user.getEmail()
        );
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        // Find user by username
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidUsernamePasswordException("Invalid username or password"));

        // Validate password
        if (request.getPassword().equals(user.getPassword())) {
            return new LoginResponse(
                    user.getUsername(),
                    user.getEmail()
            );
        } else {
            throw new InvalidUsernamePasswordException("Invalid username or password");
        }
    }
}
