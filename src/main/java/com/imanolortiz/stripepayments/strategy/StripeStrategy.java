package com.imanolortiz.stripepayments.strategy;

import com.stripe.model.Event;

public interface StripeStrategy {

    boolean isApplicable(Event event);

    Event process(Event event);

}
