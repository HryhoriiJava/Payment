package com.payment.api.resource;

import com.payment.api.dto.PaymentRequestDto;
import com.payment.api.dto.PaymentResponseDto;
import com.payment.api.dto.SalesRequestDto;
import com.payment.api.dto.SalesResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api")
public interface PaymentResourceApi {
    @PostMapping("/payment")
    ResponseEntity<PaymentResponseDto> processPayment(@RequestBody PaymentRequestDto request);

    @PostMapping("/sales")
    SalesResponseDto findSalesByDateRange(@RequestBody SalesRequestDto salesRequestDto);
}
