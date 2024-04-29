package com.imanolortiz.stripepayments.repositories;

import com.imanolortiz.stripepayments.commons.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByPaymentIntentId(String paymentId);

}
