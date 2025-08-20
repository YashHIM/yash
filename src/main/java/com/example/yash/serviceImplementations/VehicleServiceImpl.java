package com.example.yash.serviceImplementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.yash.dtos.VehicleCreationDTO;
import com.example.yash.dtos.VehicleResponseDTO;
import com.example.yash.dtos.VehicleUpdationRequestDTO;
import com.example.yash.entities.Dealer;
import com.example.yash.entities.Vehicle;
import com.example.yash.enums.SubscriptionTypes;
import com.example.yash.repositories.VehicleRepository;
import com.example.yash.services.DealerService;
import com.example.yash.services.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	private final VehicleRepository vehicleRepository;
	private final DealerService dealerService;

	public VehicleServiceImpl(VehicleRepository vehicleRepository, DealerService dealerService) {
		this.vehicleRepository = vehicleRepository;
		this.dealerService = dealerService;
	}

	@Override
	public List<VehicleResponseDTO> getAllVehicles() {
		return vehicleRepository.findAllVehicles();
	}

	@Override
	public Optional<VehicleResponseDTO> getVehicleById(Integer id) {
		return vehicleRepository.findVehicleById(id);
	}

	@Override
	public Vehicle createVehicle(VehicleCreationDTO vehicleCreationDTO) throws Exception {
		Optional<Dealer> optionalDealer = dealerService.getById(vehicleCreationDTO.getDealerId());

		if (optionalDealer.isEmpty()) {
			throw new Exception("No dealer found with given dealer id.");
		}

		Vehicle vehicle = new Vehicle();
		vehicle.setDealer(optionalDealer.get());
		vehicle.setModel(vehicleCreationDTO.getModel());
		vehicle.setPrice(vehicleCreationDTO.getPrice());
		vehicle.setStatus(vehicleCreationDTO.getStatus());

		return vehicleRepository.save(vehicle);
	}

	@Override
	public Vehicle updateVehicle(VehicleUpdationRequestDTO vehicleUpdationRequestDTO) throws Exception {
		Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleUpdationRequestDTO.getId());

		if (optionalVehicle.isEmpty()) {
			throw new Exception("No vehicle found with given id.");
		}

		Optional<Dealer> optionalDealer = dealerService.getById(vehicleUpdationRequestDTO.getDealerId());

		if (optionalDealer.isEmpty()) {
			throw new Exception("No dealer found with given dealer id.");
		}

		Vehicle vehicle = optionalVehicle.get();
		vehicle.setDealer(optionalDealer.get());
		vehicle.setModel(vehicleUpdationRequestDTO.getModel());
		vehicle.setPrice(vehicleUpdationRequestDTO.getPrice());
		vehicle.setStatus(vehicleUpdationRequestDTO.getStatus());

		return vehicleRepository.save(vehicle);
	}

	@Override
	public void deleteVehicle(Integer id) throws Exception {
		Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);

		if (optionalVehicle.isEmpty()) {
			throw new Exception("No vehicle found with given id.");
		}

		vehicleRepository.delete(optionalVehicle.get());

	}

	@Override
	public List<VehicleResponseDTO> getAllVehiclesByDealerSubscriptionType(SubscriptionTypes subscriptionType) {
		return vehicleRepository.findAllVehiclesByDealerSubscriptionType(subscriptionType);
	}
}
