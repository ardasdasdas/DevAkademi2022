package com.sahibinden.devakademi.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahibinden.devakademi.demo.model.Coordinate;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate, Integer> {

	Optional<Coordinate> findByXAndY(double x, double y);
}
