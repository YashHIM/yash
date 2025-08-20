package com.example.yash.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.yash.dtos.VehicleCreationDTO;
import com.example.yash.dtos.VehicleResponseDTO;
import com.example.yash.dtos.VehicleUpdationRequestDTO;
import com.example.yash.entities.Vehicle;
import com.example.yash.enums.SubscriptionTypes;
import com.example.yash.services.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

	private final VehicleService vehicleService;

	public VehicleController(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	@GetMapping
	@Operation(summary = "Get list of all present vehicles.")
	public ResponseEntity<?> getAllVehicles() {
		List<VehicleResponseDTO> vehicles = vehicleService.getAllVehicles();

		if (vehicles.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No vehicle found.");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(vehicles);
		}

	}

	@GetMapping("/{id}")
	@Operation(summary = "Get vehicle by id.")
	public ResponseEntity<?> getAllVehicleById(@PathVariable @Valid Integer id) {
		Optional<VehicleResponseDTO> vehicle = vehicleService.getVehicleById(id);

		if (!vehicle.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No vehicle found.");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(vehicle);
		}
	}

	@PostMapping
	@Operation(summary = "Add new vehicle.")
	public ResponseEntity<?> addVehicle(@RequestBody @Valid VehicleCreationDTO vehicleCreationDTO) throws Exception {
		try {
			Vehicle vehicle = vehicleService.createVehicle(vehicleCreationDTO);

			if (vehicle == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some error occured while creating vehicle.");
			} else {
				return ResponseEntity.status(HttpStatus.OK).body("Vehicle created successfully.");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping
	@Operation(summary = "Update vehicle by id and data.")
	public ResponseEntity<?> updateVehicle(@RequestBody @Valid VehicleUpdationRequestDTO vehicleUpdationRequestDTO)
			throws Exception {
		try {
			Vehicle vehicle = vehicleService.updateVehicle(vehicleUpdationRequestDTO);

			if (vehicle == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some error occured while updating vehicle.");
			} else {
				return ResponseEntity.status(HttpStatus.OK).body("Vehicle details updated successfully.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete vehicle by id.")
	public ResponseEntity<?> deleteVehicle(@PathVariable @Valid Integer id) throws Exception {
		try {
			vehicleService.deleteVehicle(id);

			return ResponseEntity.status(HttpStatus.OK).body("Vehicle deleted successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/dealers/premium")
	@Operation(summary = "Get all vehicles belonging to PREMIUM dealers only.")
	public ResponseEntity<?> getAllPremiumDealersVehicles() {
		List<VehicleResponseDTO> vehicle = vehicleService
				.getAllVehiclesByDealerSubscriptionType(SubscriptionTypes.PREMIUM);

		if (vehicle.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No vehicle found.");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(vehicle);
		}
	}
}
