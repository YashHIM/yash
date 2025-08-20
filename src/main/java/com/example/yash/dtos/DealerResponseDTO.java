package com.example.yash.dtos;

import com.example.yash.enums.SubscriptionTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealerResponseDTO {

	private Integer id;
	private String name;
	private String email;
	private SubscriptionTypes subscriptionType;
}
