package com.payment.provider.payment.impl;

import com.payment.api.dto.PaymentRequestDto;
import com.payment.provider.model.Payment;
import com.payment.provider.payment.PaymentGenerator;
import com.payment.provider.repository.PaymentRepository;
import com.payment.provider.converter.JpaJsonConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ChequePayment implements PaymentGenerator {

    private final PaymentRepository paymentRepository;

    private final JpaJsonConverter jpaJsonConverter;

    private final String paymentMethod = "CHEQUE";

    private final BigDecimal pointFactor = new BigDecimal("0.00");

    @Override
    public Payment processPayment(PaymentRequestDto request) {
        return save(request);
    }

    @Override
    public String getPaymentMethod() {
        return paymentMethod;
    }

    private Payment save(PaymentRequestDto request) {
        Payment payment = Payment.builder()
                .sales(request.getPrice().multiply(request.getPriceModifier()))
                .points(request.getPrice().multiply(pointFactor))
                .datetime(request.getDatetime())
                .additionalItem(jpaJsonConverter.convertToDatabaseColumn(request.getAdditionalItem()))
                .customerId(request.getCustomerId())
                .build();
        return paymentRepository.save(payment);
    }
}
