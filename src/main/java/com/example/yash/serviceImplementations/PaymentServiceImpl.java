package com.example.yash.serviceImplementations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.example.yash.dtos.PaymentCreationDTO;
import com.example.yash.entities.Dealer;
import com.example.yash.entities.Payment;
import com.example.yash.enums.PaymentStatuses;
import com.example.yash.repositories.PaymentRepository;
import com.example.yash.services.DealerService;
import com.example.yash.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	private final PaymentRepository paymentRepository;
	private final DealerService dealerService;

	public PaymentServiceImpl(PaymentRepository paymentRepository, DealerService dealerService) {
		this.paymentRepository = paymentRepository;
		this.dealerService = dealerService;
	}

	@Override
	public void initiatePayment(PaymentCreationDTO paymentCreationDTO) throws Exception {
		Optional<Dealer> optionalDealer = dealerService.getById(paymentCreationDTO.getDealerId());

		if (optionalDealer.isEmpty()) {
			throw new Exception("Dealer not found with given id.");
		}

		Payment payment = new Payment();
		payment.setDealer(optionalDealer.get());
		payment.setAmount(paymentCreationDTO.getAmount());
		payment.setMethod(paymentCreationDTO.getMethod());
		payment.setStatus(PaymentStatuses.PENDING);
		payment.setInitiatedAt(LocalDateTime.now());

		Payment savedPayment = paymentRepository.save(payment);

		CompletableFuture.runAsync(() -> {
			try {
				Thread.sleep(5000);
				savedPayment.setStatus(PaymentStatuses.SUCCESS);
				paymentRepository.save(savedPayment);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new RuntimeException("Payment update interrupted", e);
			}
		});

	}
}
