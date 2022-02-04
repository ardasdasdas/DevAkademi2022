package com.sahibinden.devakademi.demo.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sahibinden.devakademi.demo.exception.NotFoundException;
import com.sahibinden.devakademi.demo.exception.PageNotFoundException;
import com.sahibinden.devakademi.demo.model.AccessLog;
import com.sahibinden.devakademi.demo.repository.AccessLogRepository;
import com.sahibinden.devakademi.demo.response.PageableResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AccessLogService {

	private AccessLogRepository accessLogRepository;

	public PageableResponse<AccessLog> getAccessLogsByPage(Integer pageNo, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		if (!pageable.isUnpaged()) {
			return new PageableResponse<>(accessLogRepository.findAll(pageable));
		} else {
			throw new PageNotFoundException("Sayfa bilgisi bulunmamaktadır!");
		}
	}

	public AccessLog findById(int id) {

		return accessLogRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("%d id'li erişim logu bulunamadı!", id)));
	}

	@Transactional
	public List<AccessLog> saveAccessLogs(List<AccessLog> accessLogs) {
		List<AccessLog> returnAccessLogsValue = accessLogRepository.saveAll(accessLogs);
		log.info("Erişim Logları başarılı bir şekilde eklenmiştir.");

		return returnAccessLogsValue;
	}

	@Transactional
	public AccessLog updateAccessLog(AccessLog accessLog) {
		int id = accessLog.getId();
		if (accessLogRepository.existsById(id)) {
			AccessLog returnAccessLogValue = accessLogRepository.save(accessLog);
			log.info(String.format("%d id'li erişim logu başarılı bir şekilde güncellendi.", id));
			return returnAccessLogValue;
		}
		throw new NotFoundException(String.format("%d id'li erişim logu bulunamadı!", id));
	}

	@Transactional
	public int deleteAccessLogs(List<Integer> idList) {
		int deletedObjectCount = accessLogRepository.deleteAll(idList);
		log.info(String.format("%d adet erişim logu silindmiştir.", deletedObjectCount));
		return deletedObjectCount;
	}

}
