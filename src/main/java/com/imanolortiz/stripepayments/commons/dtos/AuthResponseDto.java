package com.imanolortiz.stripepayments.commons.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDto {
    private String customerId;
    private String productId;
}
