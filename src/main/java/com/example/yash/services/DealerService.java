package com.example.yash.services;

import java.util.List;
import java.util.Optional;

import com.example.yash.dtos.DealerCreationDTO;
import com.example.yash.dtos.DealerResponseDTO;
import com.example.yash.dtos.DealerUpdateRequestDTO;
import com.example.yash.entities.Dealer;

public interface DealerService {

	List<DealerResponseDTO> getAllDealers();

	Optional<DealerResponseDTO> getDealerById(Integer id);

	Dealer createDealer(DealerCreationDTO dealerCreationDTO);

	Dealer updateDealer(DealerUpdateRequestDTO dealerUpdateRequestDTO) throws Exception;

	void deleteDealer(Integer id) throws Exception;

	Optional<Dealer> getById(Integer id);

}
