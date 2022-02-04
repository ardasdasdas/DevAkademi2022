package com.sahibinden.devakademi.demo.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sahibinden.devakademi.demo.exception.NotFoundException;
import com.sahibinden.devakademi.demo.exception.PageNotFoundException;
import com.sahibinden.devakademi.demo.model.Advertisement;
import com.sahibinden.devakademi.demo.repository.AdvertisementRepository;
import com.sahibinden.devakademi.demo.response.PageableResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AdvertisementService {

	private AdvertisementRepository advertisementRepository;

	@Transactional
	public PageableResponse<Advertisement> searchAdvertisement(String searchParam, Integer pageNo, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		if (pageable.isPaged()) {
			return new PageableResponse<>(advertisementRepository.searchAdvertisement(searchParam, pageable));
		} else {
			throw new PageNotFoundException("Sayfa bilgisi bulunmamaktadır!");
		}
	}

	@Transactional
	public List<Advertisement> saveAdvertisements(List<Advertisement> advertisement) {
		List<Advertisement> returnAccessLogsValue = advertisementRepository.saveAll(advertisement);
		log.info("İlanlar başarılı bir şekilde eklenmiştir.");
		return returnAccessLogsValue;
	}

	@Transactional
	public Advertisement updateAdvertisement(Advertisement advertisement) {
		int id = advertisement.getId();
		if (advertisementRepository.existsById(id)) {
			Advertisement returnAccessLogValue = advertisementRepository.save(advertisement);
			log.info(String.format("%d id'li 'lan başarılı bir şekilde güncellendi.", id));
			return returnAccessLogValue;
		}
		throw new NotFoundException(String.format("%d id'li 'lan bulunamadı!", id));
	}

	@Transactional
	public int deleteAdvertisement(List<Integer> idList) {
		int deletedObjectCount = advertisementRepository.deleteAll(idList);
		log.info(String.format("%d adet 'lan silindmiştir.", deletedObjectCount));
		return deletedObjectCount;
	}
}
