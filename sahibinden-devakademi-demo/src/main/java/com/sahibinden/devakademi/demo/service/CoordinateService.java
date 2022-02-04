package com.sahibinden.devakademi.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sahibinden.devakademi.demo.model.Coordinate;
import com.sahibinden.devakademi.demo.repository.CoordinateRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CoordinateService {

	private CoordinateRepository coordinateRepository;

	@Transactional
	public Coordinate saveCoordinate(Coordinate coordinate) {
		double x = coordinate.getX();
		double y = coordinate.getY();
		Coordinate returnCoordinateValue = coordinateRepository.findByXAndY(x, y)
				.orElse(coordinateRepository.save(coordinate));
		log.info(String.format("x: %f | y: %f koordinatları başarılı bir şekilde eklenmiştir.", x, y));
		return returnCoordinateValue;
	}

}
