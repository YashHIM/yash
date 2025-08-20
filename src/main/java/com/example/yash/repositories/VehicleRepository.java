package com.example.yash.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.yash.dtos.VehicleResponseDTO;
import com.example.yash.entities.Vehicle;
import com.example.yash.enums.SubscriptionTypes;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

	@Query("SELECT new com.example.yash.dtos.VehicleResponseDTO(v.id, v.dealer.name, v.model, v.price, v.status) from Vehicle v order by v.id")
	List<VehicleResponseDTO> findAllVehicles();

	@Query("SELECT new com.example.yash.dtos.VehicleResponseDTO(v.id, v.dealer.name, v.model, v.price, v.status) from Vehicle v where v.id = :id")
	Optional<VehicleResponseDTO> findVehicleById(Integer id);

	@Query("SELECT new com.example.yash.dtos.VehicleResponseDTO(v.id, v.dealer.name, v.model, v.price, v.status) from Vehicle v where v.dealer.subscriptionType = :subscriptionType")
	List<VehicleResponseDTO> findAllVehiclesByDealerSubscriptionType(SubscriptionTypes subscriptionType);

}
