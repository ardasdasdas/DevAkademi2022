package com.sahibinden.devakademi.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "classified_attribute")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClassifiedAttribute {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@ManyToOne
	@JoinColumn(name = "classified_id")
	Classified classified;

	@Column(name = "attribute_name", length = 32, nullable = false)
	String attributeName;

	@Column(name = "attribute_value", length = 32, nullable = false)
	String attributeValue;

}
