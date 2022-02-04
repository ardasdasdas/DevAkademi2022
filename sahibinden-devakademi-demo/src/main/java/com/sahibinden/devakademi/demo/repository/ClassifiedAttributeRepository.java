package com.sahibinden.devakademi.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahibinden.devakademi.demo.model.ClassifiedAttribute;

@Repository
public interface ClassifiedAttributeRepository extends JpaRepository<ClassifiedAttribute, Integer> {

}
