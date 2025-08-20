package com.example.yash.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCreationDTO {

	@NotNull(message = "Dealer's id is required")
	private Integer dealerId;

	@NotNull(message = "Amount is required")
	private Double amount;

	@NotBlank(message = "Mothod is required")
	private String method;
}
