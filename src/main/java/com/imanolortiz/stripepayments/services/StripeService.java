package com.imanolortiz.stripepayments.services;

import com.imanolortiz.stripepayments.commons.dtos.CheckoutRequest;
import com.imanolortiz.stripepayments.commons.dtos.CheckoutResponse;
import com.stripe.model.Customer;
import com.stripe.model.Event;
import com.stripe.model.Price;
import com.stripe.model.Product;

public interface StripeService {
    void manageWebHook(Event event);
    Event constructEvent(String payload, String stripeHeader);
    Customer createCustomer(String email);
    Product createProduct(String name);
    Price createPrice(String productId);
    CheckoutResponse createCheckout(CheckoutRequest checkoutRequest);
}
