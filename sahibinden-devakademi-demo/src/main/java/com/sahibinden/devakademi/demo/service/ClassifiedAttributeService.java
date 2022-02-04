package com.sahibinden.devakademi.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sahibinden.devakademi.demo.model.ClassifiedAttribute;
import com.sahibinden.devakademi.demo.repository.ClassifiedAttributeRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ClassifiedAttributeService {

	private ClassifiedAttributeRepository classifiedAttributeRepository;

	@Transactional
	public List<ClassifiedAttribute> saveClassifiedAttributes(List<ClassifiedAttribute> classifiedAttributes) {
		List<ClassifiedAttribute> returnClassifiedsValue = classifiedAttributeRepository.saveAll(classifiedAttributes);
		log.info("Classified Attributes başarılı bir şekilde eklenmiştir.");
		return returnClassifiedsValue;
	}

}
