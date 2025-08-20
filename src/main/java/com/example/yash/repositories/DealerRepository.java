package com.example.yash.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.yash.dtos.DealerResponseDTO;
import com.example.yash.entities.Dealer;

public interface DealerRepository extends JpaRepository<Dealer, Integer> {

	@Query("SELECT new com.example.yash.dtos.DealerResponseDTO(d.id, d.name, d.email, d.subscriptionType) from Dealer d order by d.id")
	List<DealerResponseDTO> findAllDealers();

	@Query("SELECT new com.example.yash.dtos.DealerResponseDTO(d.id, d.name, d.email, d.subscriptionType) from Dealer d where d.id = :id")
	Optional<DealerResponseDTO> findDealerById(Integer id);

}
