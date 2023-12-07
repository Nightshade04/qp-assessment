package com.vibhanshu.qpassessment.services.impl;

import com.vibhanshu.qpassessment.entities.Role;
import com.vibhanshu.qpassessment.entities.User;
import com.vibhanshu.qpassessment.models.AuthRequestModel;
import com.vibhanshu.qpassessment.models.AuthResponseModel;
import com.vibhanshu.qpassessment.models.TokenRefreshModel;
import com.vibhanshu.qpassessment.repositories.UserRepository;
import com.vibhanshu.qpassessment.services.AuthService;
import com.vibhanshu.qpassessment.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
//@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Override
    public User signUp(AuthRequestModel model) {
        User user = new User();
        user.setUsername(model.getUsername());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(model.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public AuthResponseModel signIn(AuthRequestModel model) {

        AuthResponseModel responseModel = new AuthResponseModel();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword()));

        var user = userRepository.findByUsername(model.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid Username or Password!"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        responseModel.setToken(jwt);
        responseModel.setRefreshToken(refreshToken);

        return responseModel;
    }

    @Override
    public AuthResponseModel refreshToken(TokenRefreshModel tokenRefreshModel) {
        String username = jwtService.extractUsername(tokenRefreshModel.getRefreshToken());
        User user = userRepository.findByUsername(username).orElseThrow();
        AuthResponseModel responseModel = new AuthResponseModel();

        if (jwtService.isTokenValid(tokenRefreshModel.getRefreshToken(), user)) {
            var jwt = jwtService.generateToken(user);
            responseModel.setToken(jwt);
            responseModel.setRefreshToken(tokenRefreshModel.getRefreshToken());
        }
        return responseModel;
    }

}
