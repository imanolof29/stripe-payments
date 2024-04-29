package com.imanolortiz.stripepayments.commons.entities;

import com.imanolortiz.stripepayments.commons.enums.StripeEventEnum;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
@Entity
@Getter
@Setter
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String paymentIntentId;

    private String customerId;

    private String productId;

    private Long amount;

    private String currency;

    @Enumerated(EnumType.STRING)
    private StripeEventEnum type;

}
