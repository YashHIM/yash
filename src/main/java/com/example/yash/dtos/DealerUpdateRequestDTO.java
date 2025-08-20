package com.example.yash.dtos;

import com.example.yash.enums.SubscriptionTypes;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealerUpdateRequestDTO {

	@NotNull(message = "Id is required")
	private Integer id;

	@NotBlank(message = "Name is required")
	private String name;

	@Email(message = "Email should be valid")
	@NotBlank(message = "Email is required")
	private String email;

	@NotNull(message = "Subscription type is required")
	private SubscriptionTypes subscriptionType;
}
