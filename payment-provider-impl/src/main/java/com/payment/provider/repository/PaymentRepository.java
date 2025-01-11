package com.payment.provider.repository;

import com.payment.provider.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query(value = "SELECT\n" +
            "    DATE_TRUNC('hour', datetime) AS datetime,\n" +
            "    SUM(sales) AS sales,\n" +
            "    SUM(points) AS points\n" +
            "FROM payment\n" +
            "WHERE datetime >= :startDateTime AND datetime <= :endDateTime\n" +
            "GROUP BY DATE_TRUNC('hour', datetime)\n" +
            "ORDER BY DATE_TRUNC('hour', datetime)", nativeQuery = true)
    List<Object[]> findSalesByDateRange(@Param("startDateTime") Instant startDateTime, @Param("endDateTime") Instant endDateTime);
}
