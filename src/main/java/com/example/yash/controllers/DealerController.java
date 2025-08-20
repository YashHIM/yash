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

import com.example.yash.dtos.DealerCreationDTO;
import com.example.yash.dtos.DealerResponseDTO;
import com.example.yash.dtos.DealerUpdateRequestDTO;
import com.example.yash.entities.Dealer;
import com.example.yash.services.DealerService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/dealer")
public class DealerController {

	private final DealerService dealerService;

	public DealerController(DealerService dealerService) {
		this.dealerService = dealerService;
	}

	@GetMapping
	@Operation(summary = "Get list of all present dealers.")
	public ResponseEntity<?> getAllDealers() {
		List<DealerResponseDTO> dealers = dealerService.getAllDealers();

		if (dealers.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No dealer found.");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(dealers);
		}

	}

	@GetMapping("/{id}")
	@Operation(summary = "Get dealer by id.")
	public ResponseEntity<?> getAllDealerById(@PathVariable @Valid Integer id) {
		Optional<DealerResponseDTO> dealer = dealerService.getDealerById(id);

		if (!dealer.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No dealer found.");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(dealer);
		}
	}

	@PostMapping
	@Operation(summary = "Add new dealer.")
	public ResponseEntity<?> addDealer(@RequestBody @Valid DealerCreationDTO dealerCreationDTO) {
		Dealer dealer = dealerService.createDealer(dealerCreationDTO);

		if (dealer == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some error occured while creating dealer.");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("Dealer created successfully.");
		}
	}

	@PutMapping
	@Operation(summary = "Update dealer by id and data.")
	public ResponseEntity<?> updateDealer(@RequestBody @Valid DealerUpdateRequestDTO dealerUpdateRequestDTO)
			throws Exception {
		try {
			Dealer dealer = dealerService.updateDealer(dealerUpdateRequestDTO);

			if (dealer == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some error occured while updating dealer.");
			} else {
				return ResponseEntity.status(HttpStatus.OK).body("Dealer details updated successfully.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete dealer by id.")
	public ResponseEntity<?> deleteDealer(@PathVariable @Valid Integer id) throws Exception {
		try {
			dealerService.deleteDealer(id);

			return ResponseEntity.status(HttpStatus.OK).body("Dealer deleted successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
