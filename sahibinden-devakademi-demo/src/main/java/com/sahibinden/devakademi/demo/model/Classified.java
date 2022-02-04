package com.sahibinden.devakademi.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "classified")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Classified {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;

	@Column(name = "title", length = 225, nullable = false)
	String title;

	@Column(name = "description", length = 225, nullable = false)
	String description;

	@Column(name = "currency", length = 32, nullable = false)
	String currency;

	@Column(name = "price", nullable = false)
	double price;

	@Column(name = "status", length = 32, nullable = false)
	String status;

	@Column(name = "created_date", nullable = false)
	Date createdDate;

	@Column(name = "published_by", length = 32, nullable = false)
	String publishedBy;

	@Column(name = "city", length = 32, nullable = false)
	String city;

	@Column(name = "category", length = 32, nullable = false)
	String category;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "classified")
	@JsonIgnoreProperties("classified")
	List<ClassifiedAttribute> classifiedAttributes;

	@ManyToOne
	@JoinColumn(name = "coordinate_id")
	Coordinate coordinate;

	@OneToOne(mappedBy = "classified")
	@JoinColumn(name = "payment_detail_id", nullable = false, foreignKey = @ForeignKey(name = "FK_Classified_PaymentDetail"))
	PaymentDetail paymentDetail;
}
