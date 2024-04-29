package com.imanolortiz.stripepayments.controllers.impl;

import com.imanolortiz.stripepayments.commons.dtos.CheckoutRequest;
import com.imanolortiz.stripepayments.commons.dtos.CheckoutResponse;
import com.imanolortiz.stripepayments.controllers.StripeApi;
import com.imanolortiz.stripepayments.services.StripeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StripeController implements StripeApi {

    private final StripeService stripeService;

    public StripeController(StripeService stripeService){
        this.stripeService = stripeService;
    }

    @Override
    public ResponseEntity<Void> stripeWebHook(String payload, String stripeHeader) {
        var event = stripeService.constructEvent(payload, stripeHeader);
        stripeService.manageWebHook(event);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CheckoutResponse> createCheckout(CheckoutRequest checkoutRequest) {
        return ResponseEntity.ok(stripeService.createCheckout(checkoutRequest));
    }

}
