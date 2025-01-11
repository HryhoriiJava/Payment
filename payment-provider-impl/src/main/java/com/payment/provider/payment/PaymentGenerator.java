package com.payment.provider.payment;

import com.payment.api.dto.PaymentRequestDto;
import com.payment.provider.model.Payment;

public interface PaymentGenerator {

    Payment processPayment(PaymentRequestDto request);

    String getPaymentMethod();
}
