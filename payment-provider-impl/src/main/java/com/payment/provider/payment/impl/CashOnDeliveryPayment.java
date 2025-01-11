package com.payment.provider.payment.impl;

import com.payment.api.dto.PaymentRequestDto;
import com.payment.provider.exception.UnsupportedCourierException;
import com.payment.provider.model.Payment;
import com.payment.provider.payment.PaymentGenerator;
import com.payment.provider.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CashOnDeliveryPayment implements PaymentGenerator {

    private final PaymentRepository paymentRepository;

    private final String paymentMethod = "CASH_ON_DELIVERY";

    private final List<String> courierList = new ArrayList<>(List.of("YAMATO", "SAGAWA"));

    private final BigDecimal pointFactor = new BigDecimal("0.05");

    @Override
    public Payment processPayment(PaymentRequestDto request) {
        String courier = request.getAdditionalItem().get("courier");
        if (!courierList.contains(courier)) {
            throw new UnsupportedCourierException(courier);
        }

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
