package com.payment.provider.controller;

import com.payment.api.dto.*;
import com.payment.api.resource.PaymentResourceApi;
import com.payment.provider.model.Payment;
import com.payment.provider.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.RoundingMode;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentController implements PaymentResourceApi {

    private final PaymentService paymentService;

    @Override
    public ResponseEntity<PaymentResponseDto> processPayment(PaymentRequestDto request) {
        Payment payment = paymentService.processPayment(request);
        PaymentResponseDto paymentResponseDto = PaymentResponseDto.builder()
                .finalPrice(payment.getSales().setScale(2, RoundingMode.DOWN))
                .points(payment.getPoints().setScale(0, RoundingMode.DOWN))
                .build();
        return ResponseEntity.ok(paymentResponseDto);
    }

    @Override
    public SalesResponseDto findSalesByDateRange(SalesRequestDto salesRequestDto) {
        List<SalesResponseSalesDto> sales = paymentService.findSalesByDateRange(salesRequestDto.getStartDateTime(),
                salesRequestDto.getEndDateTime());
        return SalesResponseDto.builder()
                .sales(sales)
                .build();
    }
}