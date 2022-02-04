package com.sahibinden.devakademi.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coordinate")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Coordinate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column(name = "x", nullable = false)
	double x;

	@Column(name = "y", nullable = false)
	double y;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "coordinate")
	@JsonIgnoreProperties("coordinate")
	List<Classified> classifieds;
}
