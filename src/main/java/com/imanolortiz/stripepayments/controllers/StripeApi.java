package com.imanolortiz.stripepayments.controllers;

import com.imanolortiz.stripepayments.commons.constants.ApiPathConstants;
import com.imanolortiz.stripepayments.commons.dtos.CheckoutRequest;
import com.imanolortiz.stripepayments.commons.dtos.CheckoutResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController(ApiPathConstants.V1_ROUTE + ApiPathConstants.STRIPE_ROUTE)
public interface StripeApi {

    @PostMapping(value = "/webhook")
    ResponseEntity<Void> stripeWebHook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String stripeHeader
    );

    @PostMapping(value = "/checkout")
    ResponseEntity<CheckoutResponse> createCheckout(@RequestBody @Valid CheckoutRequest checkoutRequest);

}
