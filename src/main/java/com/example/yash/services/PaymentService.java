package com.example.yash.services;

import com.example.yash.dtos.PaymentCreationDTO;

public interface PaymentService {

	void initiatePayment(PaymentCreationDTO paymentCreationDTO) throws Exception;

}
