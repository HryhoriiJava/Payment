package com.payment.provider.service;

import com.payment.api.dto.PaymentRequestDto;
import com.payment.api.dto.SalesResponseSalesDto;
import com.payment.provider.model.Payment;

import java.time.Instant;
import java.util.List;

public interface PaymentService {
    Payment processPayment(PaymentRequestDto request);

    List<SalesResponseSalesDto> findSalesByDateRange(Instant startDateTime, Instant endDateTime);
}
