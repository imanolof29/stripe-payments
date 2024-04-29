package com.imanolortiz.stripepayments.services.impl;

import com.imanolortiz.stripepayments.commons.dtos.AuthResponseDto;
import com.imanolortiz.stripepayments.commons.dtos.UserRequest;
import com.imanolortiz.stripepayments.commons.entities.UserModel;
import com.imanolortiz.stripepayments.repositories.UserRepository;
import com.imanolortiz.stripepayments.services.AuthService;
import com.imanolortiz.stripepayments.services.StripeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final StripeService stripeService;

    private final UserRepository userRepository;

    public AuthServiceImpl(StripeService stripeService, UserRepository userRepository){
        this.stripeService = stripeService;
        this.userRepository = userRepository;
    }
    @Override
    public AuthResponseDto createUser(UserRequest userRequest) {
        return Optional.of(userRequest)
                .map(this::mapToEntity)
                .map(this::setUserCustomerAndProduct)
                .map(userRepository::save)
                .map(userModel -> AuthResponseDto.builder()
                        .customerId(userModel.getCustomerId())
                        .productId(userModel.getProductId())
                        .build()
                )
                .orElseThrow(() -> new RuntimeException("Error creating user"));
    }

    private UserModel mapToEntity(UserRequest userRequest){
        return UserModel.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }

    private UserModel setUserCustomerAndProduct(UserModel userModel){
        var customerCreated = stripeService.createCustomer(userModel.getEmail());
        var productCreated = stripeService.createProduct(userModel.getName());
        stripeService.createPrice(productCreated.getId());

        userModel.setProductId(productCreated.getId());
        userModel.setCustomerId(customerCreated.getId());

        return userModel;
    }
}
