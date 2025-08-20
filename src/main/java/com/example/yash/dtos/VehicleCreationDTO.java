package com.example.yash.dtos;

import com.example.yash.enums.Statuses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleCreationDTO {

	@NotNull(message = "Dealer's id is required")
	private Integer dealerId;

	@NotBlank(message = "Model is required")
	private String model;

	@NotNull(message = "Price is required")
	private Integer price;

	@NotNull(message = "Status is required")
	private Statuses status;
}
