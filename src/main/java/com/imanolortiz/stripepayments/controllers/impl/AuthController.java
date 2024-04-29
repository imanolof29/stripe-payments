package com.imanolortiz.stripepayments.controllers.impl;

import com.imanolortiz.stripepayments.commons.dtos.AuthResponseDto;
import com.imanolortiz.stripepayments.commons.dtos.UserRequest;
import com.imanolortiz.stripepayments.controllers.AuthApi;
import com.imanolortiz.stripepayments.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @Override
    public ResponseEntity<AuthResponseDto> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(authService.createUser(userRequest));
    }

}
