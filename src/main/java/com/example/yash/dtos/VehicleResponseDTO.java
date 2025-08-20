package com.example.yash.dtos;

import com.example.yash.enums.Statuses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponseDTO {

	private Integer id;
	private String dealer;
	private String model;
	private Integer price;
	private Statuses status;
}
