package com.example.yash.serviceImplementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.yash.dtos.DealerCreationDTO;
import com.example.yash.dtos.DealerResponseDTO;
import com.example.yash.dtos.DealerUpdateRequestDTO;
import com.example.yash.entities.Dealer;
import com.example.yash.repositories.DealerRepository;
import com.example.yash.services.DealerService;

@Service
public class DealerServiceImpl implements DealerService {

	private final DealerRepository dealerRepository;

	public DealerServiceImpl(DealerRepository dealerRepository) {
		this.dealerRepository = dealerRepository;
	}

	@Override
	public List<DealerResponseDTO> getAllDealers() {
		return dealerRepository.findAllDealers();
	}

	@Override
	public Optional<DealerResponseDTO> getDealerById(Integer id) {
		return dealerRepository.findDealerById(id);
	}

	@Override
	public Dealer createDealer(DealerCreationDTO dealerCreationDTO) {
		Dealer dealer = new Dealer();
		dealer.setName(dealerCreationDTO.getName());
		dealer.setEmail(dealerCreationDTO.getEmail());
		dealer.setSubscriptionType(dealerCreationDTO.getSubscriptionType());

		return dealerRepository.save(dealer);
	}

	@Override
	public Dealer updateDealer(DealerUpdateRequestDTO dealerUpdateRequestDTO) throws Exception {

		Optional<Dealer> optionalDealer = dealerRepository.findById(dealerUpdateRequestDTO.getId());

		if (optionalDealer.isEmpty()) {
			throw new Exception("Dealer not found with this id.");
		}

		Dealer dealer = optionalDealer.get();

		dealer.setName(dealerUpdateRequestDTO.getName());
		dealer.setEmail(dealerUpdateRequestDTO.getEmail());
		dealer.setSubscriptionType(dealerUpdateRequestDTO.getSubscriptionType());

		return dealerRepository.save(dealer);
	}

	@Override
	public void deleteDealer(Integer id) throws Exception {
		Optional<Dealer> optionalDealer = dealerRepository.findById(id);

		if (optionalDealer.isEmpty()) {
			throw new Exception("Dealer not found with this id.");
		}

		dealerRepository.delete(optionalDealer.get());
	}

	@Override
	public Optional<Dealer> getById(Integer id) {
		return dealerRepository.findById(id);
	}
}
