package com.vibhanshu.qpassessment.services;

import com.vibhanshu.qpassessment.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

public interface JwtService {

    public String generateRefreshToken(Map<String, Object> claims, UserDetails user);

    public String extractUsername(String token);

    public String generateToken(UserDetails userDetails);

    public boolean isTokenValid(String token, UserDetails userDetails);

}
