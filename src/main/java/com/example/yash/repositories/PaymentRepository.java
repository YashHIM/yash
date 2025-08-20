package com.example.yash.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.yash.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
