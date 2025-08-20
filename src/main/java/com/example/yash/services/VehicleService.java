package com.example.yash.services;

import java.util.List;
import java.util.Optional;

import com.example.yash.dtos.VehicleCreationDTO;
import com.example.yash.dtos.VehicleResponseDTO;
import com.example.yash.dtos.VehicleUpdationRequestDTO;
import com.example.yash.entities.Vehicle;
import com.example.yash.enums.SubscriptionTypes;

public interface VehicleService {

	List<VehicleResponseDTO> getAllVehicles();

	Optional<VehicleResponseDTO> getVehicleById(Integer id);

	Vehicle createVehicle(VehicleCreationDTO vehicleCreationDTO) throws Exception;

	Vehicle updateVehicle(VehicleUpdationRequestDTO vehicleUpdationRequestDTO) throws Exception;

	void deleteVehicle(Integer id) throws Exception;

	List<VehicleResponseDTO> getAllVehiclesByDealerSubscriptionType(SubscriptionTypes subscriptionType);

}
