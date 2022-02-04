package com.sahibinden.devakademi.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column(name = "first_name", length = 32, nullable = false)
	String firstName;

	@Column(name = "last_name", length = 32, nullable = false)
	String lastName;

	@Column(name = "status", length = 21, nullable = false)
	String status;

	@OneToOne(mappedBy = "user")
	@JoinColumn(name = "access_log_id", nullable = true, foreignKey = @ForeignKey(name = "FK_User_AccessLog"))
	private AccessLog accessLog;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "user")
	@JsonIgnoreProperties("user")
	List<Classified> classifieds;

}
