package com.imanolortiz.stripepayments.services;

import com.imanolortiz.stripepayments.commons.dtos.AuthResponseDto;
import com.imanolortiz.stripepayments.commons.dtos.UserRequest;

public interface AuthService {
    AuthResponseDto createUser(UserRequest userRequest);
}
