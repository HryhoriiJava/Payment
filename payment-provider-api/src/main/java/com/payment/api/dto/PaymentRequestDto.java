package com.payment.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder(toBuilder = true)
public class PaymentRequestDto {

    String customerId;
    BigDecimal price;
    BigDecimal priceModifier;
    String paymentMethod;
    Instant datetime;
    private Map<String, String> additionalItem;
}