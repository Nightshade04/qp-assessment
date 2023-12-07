package com.vibhanshu.qpassessment.controllers;

import com.vibhanshu.qpassessment.entities.User;
import com.vibhanshu.qpassessment.models.AuthRequestModel;
import com.vibhanshu.qpassessment.models.AuthResponseModel;
import com.vibhanshu.qpassessment.models.TokenRefreshModel;
import com.vibhanshu.qpassessment.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("signup")
    public ResponseEntity<User> signUp(@RequestBody AuthRequestModel model){
        return ResponseEntity.ok(authService.signUp(model));
    }

    @PostMapping("signin")
    public ResponseEntity<AuthResponseModel> signIn(@RequestBody AuthRequestModel model){
        return ResponseEntity.ok(authService.signIn(model));
    }

    @PostMapping("refresh")
    public ResponseEntity<AuthResponseModel> refreshToken(@RequestBody TokenRefreshModel model){
        return ResponseEntity.ok(authService.refreshToken(model));
    }

}
