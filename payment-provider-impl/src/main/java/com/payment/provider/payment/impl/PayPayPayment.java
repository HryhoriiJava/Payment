package com.payment.provider.payment.impl;

import com.payment.api.dto.PaymentRequestDto;
import com.payment.provider.model.Payment;
import com.payment.provider.payment.PaymentGenerator;
import com.payment.provider.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class PayPayPayment implements PaymentGenerator {

    private final PaymentRepository paymentRepository;

    private final String paymentMethod = "PAYPAY";

    private final BigDecimal pointFactor = new BigDecimal("0.01");

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
                .customerId(request.getCustomerId())
                .build();
        return paymentRepository.save(payment);
    }
}
