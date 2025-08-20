package com.example.yash.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.yash.dtos.PaymentCreationDTO;
import com.example.yash.services.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

	private final PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@PostMapping("/initiate")
	@Operation(summary = "Initiate payment.")
	public ResponseEntity<?> initiatePayment(@RequestBody @Valid PaymentCreationDTO paymentCreationDTO)
			throws Exception {
		try {
			paymentService.initiatePayment(paymentCreationDTO);

			return ResponseEntity.status(HttpStatus.OK).body("Payment initiated.");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
