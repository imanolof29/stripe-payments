package com.imanolortiz.stripepayments.controllers;

import com.imanolortiz.stripepayments.commons.constants.ApiPathConstants;
import com.imanolortiz.stripepayments.commons.dtos.AuthResponseDto;
import com.imanolortiz.stripepayments.commons.dtos.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.AUTH_ROUTE)
public interface AuthApi {
    @PostMapping
    ResponseEntity<AuthResponseDto> createUser(@RequestBody @Valid UserRequest userRequest);
}
