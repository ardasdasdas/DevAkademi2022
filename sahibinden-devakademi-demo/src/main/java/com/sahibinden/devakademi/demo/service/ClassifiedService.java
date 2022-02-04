package com.sahibinden.devakademi.demo.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sahibinden.devakademi.demo.exception.NotFoundException;
import com.sahibinden.devakademi.demo.exception.PageNotFoundException;
import com.sahibinden.devakademi.demo.model.Classified;
import com.sahibinden.devakademi.demo.repository.ClassifiedRepository;
import com.sahibinden.devakademi.demo.response.PageableResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ClassifiedService {

	private ClassifiedRepository classifiedRepository;
	private CoordinateService coordinateRepository;
	private ClassifiedAttributeService classifiedAttributeService;

	public PageableResponse<Classified> getAccessLogsByPage(Integer pageNo, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		if (!pageable.isUnpaged()) {
			return new PageableResponse<>(classifiedRepository.findAll(pageable));
		} else {
			throw new PageNotFoundException("Sayfa bilgisi bulunmamaktadır!");
		}
	}

	public Classified findById(int id) {

		return classifiedRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("%d id'li classified bulunamadı!", id)));
	}

	@Transactional
	public List<Classified> saveClassifieds(List<Classified> classifieds) {
		for (Classified classified : classifieds) {
			coordinateRepository.saveCoordinate(classified.getCoordinate());
			classifiedAttributeService.saveClassifiedAttributes(classified.getClassifiedAttributes());
		}
		List<Classified> returnClassifiedsValue = classifiedRepository.saveAll(classifieds);
		log.info("Classifieds başarılı bir şekilde eklenmiştir.");
		return returnClassifiedsValue;
	}

	@Transactional
	public Classified updateClassified(Classified classified) {
		int id = classified.getId();
		if (classifiedRepository.existsById(id)) {
			Classified returnClassifiedValue = classifiedRepository.save(classified);
			coordinateRepository.saveCoordinate(classified.getCoordinate());
			classifiedAttributeService.saveClassifiedAttributes(classified.getClassifiedAttributes());
			log.info(String.format("%d id'li classified başarılı bir şekilde güncellendi.", id));
			return returnClassifiedValue;
		}
		throw new NotFoundException(String.format("%d id'li classified bulunamadı!", id));
	}

	@Transactional
	public int deleteClassified(List<Integer> idList) {
		int deletedObjectCount = classifiedRepository.deleteAll(idList);
		log.info(String.format("%d adet classified silindmiştir.", deletedObjectCount));
		return deletedObjectCount;
	}
}
