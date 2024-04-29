package com.imanolortiz.stripepayments.strategy.impl;

import com.imanolortiz.stripepayments.commons.entities.Payment;
import com.imanolortiz.stripepayments.commons.enums.StripeEventEnum;
import com.imanolortiz.stripepayments.repositories.PaymentRepository;
import com.imanolortiz.stripepayments.strategy.StripeStrategy;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StripeStrategyPaymentIntentSucceed implements StripeStrategy {

    private final PaymentRepository paymentRepository;

    public StripeStrategyPaymentIntentSucceed(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    @Override
    public boolean isApplicable(Event event) {
        return StripeEventEnum.PAYMENT_INTENT_SUCCEED.value.equals(event.getType());
    }

    @Override
    public Event process(Event event) {
        return Optional.of(event)
                .map(this::deserialize)
                .map(this::mapToEntity)
                .map(paymentRepository::save)
                .map(given -> event)
                .orElseThrow(() -> new RuntimeException("Error processing"));
    }

    private PaymentIntent deserialize(Event event){
        return (PaymentIntent) event
                .getDataObjectDeserializer()
                .getObject()
                .orElseThrow(() -> new RuntimeException("Error deserializing object"));
    }

    private Payment mapToEntity(PaymentIntent paymentIntent){
        return Payment
                .builder()
                .paymentIntentId(paymentIntent.getId())
                .customerId(paymentIntent.getCustomer())
                .amount(paymentIntent.getAmount())
                .currency(paymentIntent.getCurrency())
                .type(StripeEventEnum.PAYMENT_INTENT_SUCCEED)
                .build();
    }

}
