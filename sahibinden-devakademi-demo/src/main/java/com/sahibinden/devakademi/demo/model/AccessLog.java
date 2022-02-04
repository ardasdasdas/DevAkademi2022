package com.sahibinden.devakademi.demo.model;

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
@Table(name = "access_log")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccessLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = true, foreignKey = @ForeignKey(name = "FK_AccessLog_User"))
	User user;

	@Column(name = "endpoint", length = 225, nullable = false)
	String endpoint;

	@Column(name = "created_date", nullable = false)
	long createdDate;

}
