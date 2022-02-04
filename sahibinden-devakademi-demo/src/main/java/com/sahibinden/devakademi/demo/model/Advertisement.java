package com.sahibinden.devakademi.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "advertisement")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Advertisement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column(name = "category", length = 32, nullable = false)
	String category;

	@Column(name = "impression_count", nullable = false)
	int impressionCount;

	@Column(name = "click_count", nullable = false)
	int clickCount;

	@Column(name = "content", length = 32, nullable = false)
	String content;

	@Column(name = "content_type", length = 32, nullable = false)
	String contentType;

	@Column(name = "created_date", length = 32, nullable = false)
	String createdDate;
}
