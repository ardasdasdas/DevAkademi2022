package com.sahibinden.devakademi.demo.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sahibinden.devakademi.demo.exception.NotFoundException;
import com.sahibinden.devakademi.demo.exception.PageNotFoundException;
import com.sahibinden.devakademi.demo.model.PaymentDetail;
import com.sahibinden.devakademi.demo.repository.PaymentDetailRepository;
import com.sahibinden.devakademi.demo.response.PageableResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentDetailService {

	private PaymentDetailRepository paymentDetailRepository;

	public PageableResponse<PaymentDetail> getAccessLogsByPage(Integer pageNo, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		if (!pageable.isUnpaged()) {
			return new PageableResponse<>(paymentDetailRepository.findAll(pageable));
		} else {
			throw new PageNotFoundException("Sayfa bilgisi bulunmamaktadır!");
		}
	}

	public PaymentDetail findById(int id) {

		return paymentDetailRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("%d id'li ödeme detayı bulunamadı!", id)));
	}

	@Transactional
	public List<PaymentDetail> savePaymentDetails(List<PaymentDetail> paymentDetails) {
		List<PaymentDetail> returnPaymentDetailsValue = paymentDetailRepository.saveAll(paymentDetails);
		log.info("Ödeme detayları başarılı bir şekilde eklenmiştir.");
		return returnPaymentDetailsValue;
	}

	@Transactional
	public PaymentDetail updatePaymentDetail(PaymentDetail paymentDetail) {
		int id = paymentDetail.getId();
		if (paymentDetailRepository.existsById(id)) {
			PaymentDetail returnUserValue = paymentDetailRepository.save(paymentDetail);
			log.info(String.format("%d id'li ödeme detayı başarılı bir şekilde güncellendi.", id));
			return returnUserValue;
		}
		throw new NotFoundException(String.format("%d id'li ödeme detayı bulunamadı!", id));
	}

	@Transactional
	public int deletePaymentDetails(List<Integer> idList) {
		int deletedObjectCount = paymentDetailRepository.deleteAll(idList);
		log.info(String.format("%d adet ödeme detayı silindmiştir.", deletedObjectCount));
		return deletedObjectCount;
	}
}
