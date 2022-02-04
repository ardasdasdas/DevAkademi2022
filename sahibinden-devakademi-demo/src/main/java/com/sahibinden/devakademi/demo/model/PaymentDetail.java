package com.sahibinden.devakademi.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment_detail")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@OneToOne
	@JoinColumn(name = "classified_id", nullable = true, foreignKey = @ForeignKey(name = "FK_PaymentDetail_Classified"))
	Classified classified;

	@Column(name = "created_date", nullable = false)
	Date createdDate;

	@Column(name = "amount", nullable = false)
	double amount;

	@Column(name = "discount", nullable = false)
	int discount;

}
