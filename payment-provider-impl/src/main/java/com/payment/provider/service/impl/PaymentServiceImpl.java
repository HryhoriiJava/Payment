package com.payment.provider.service.impl;

import com.payment.api.dto.PaymentRequestDto;
import com.payment.api.dto.SalesResponseSalesDto;
import com.payment.provider.exception.UnsupportedPaymentMethodException;
import com.payment.provider.model.Payment;
import com.payment.provider.payment.PaymentGenerator;
import com.payment.provider.repository.PaymentRepository;
import com.payment.provider.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final Map<String, PaymentGenerator> map;

    @Autowired
    public PaymentServiceImpl(List<PaymentGenerator> paymentMethods, PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
        this.map = paymentMethods.stream()
                .collect(Collectors.toMap(PaymentGenerator::getPaymentMethod,
                        paymentGenerator -> paymentGenerator));
    }

    @Override
    public Payment processPayment(PaymentRequestDto request) {
        PaymentGenerator paymentGenerator = Optional.ofNullable(map.get(request.getPaymentMethod()))
                .orElseThrow(() -> new UnsupportedPaymentMethodException(request.getPaymentMethod()));
        return paymentGenerator.processPayment(request);
    }

    @Override
    public List<SalesResponseSalesDto> findSalesByDateRange(Instant startDateTime, Instant endDateTime) {
        List<Object[]> salesByDateRange = paymentRepository.findSalesByDateRange(startDateTime, endDateTime);
        return toSalesResponseSalesDtoList(salesByDateRange);
    }

    private static List<SalesResponseSalesDto> toSalesResponseSalesDtoList(List<Object[]> salesByDateRange) {
        return salesByDateRange.stream()
                .map(result -> new SalesResponseSalesDto(
                        (BigDecimal) result[1],
                        (BigDecimal) result[2],
                        (Instant) result[0]))
                .collect(Collectors.toList());
    }
}