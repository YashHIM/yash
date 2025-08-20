package com.example.yash.entities;

import java.time.LocalDateTime;

import com.example.yash.enums.PaymentStatuses;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JoinColumn(name = "dealer_id")
	@ManyToOne
	private Dealer dealer;

	private Double amount;
	private String method;

	@Enumerated(EnumType.STRING)
	private PaymentStatuses status;

	private LocalDateTime initiatedAt;
}
