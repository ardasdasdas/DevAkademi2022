package com.sahibinden.devakademi.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sahibinden.devakademi.demo.exception.NotFoundException;
import com.sahibinden.devakademi.demo.model.PaymentDetail;
import com.sahibinden.devakademi.demo.repository.PaymentDetailRepository;

public class PaymentDetailServiceTest {

	private PaymentDetailService paymentDetailService;

	@Mock
	private PaymentDetailRepository paymentDetailRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		paymentDetailService = new PaymentDetailService(paymentDetailRepository);
	}

	@Test
	void given_paymentDetails_when_addPaymentDetails_will_return_paymentDetails() {
		List<PaymentDetail> paymentDetails = createTesPaymentDetailList(3);
		given(paymentDetailRepository.saveAll(paymentDetails)).willReturn(paymentDetails);

		List<PaymentDetail> testPaymentDetails = paymentDetailService.savePaymentDetails(paymentDetails);

		for (int i = 0; i < paymentDetails.size(); i++) {
			assertEquals(paymentDetails.get(i), testPaymentDetails.get(i));
			assertThat(paymentDetails.get(i).getId()).isNotNull().isEqualTo(testPaymentDetails.get(i).getId());
			assertThat(paymentDetails.get(i).getAmount()).isEqualTo(testPaymentDetails.get(i).getAmount());
			assertThat(paymentDetails.get(i).getClassified()).isEqualTo(testPaymentDetails.get(i).getClassified());
			assertThat(paymentDetails.get(i).getCreatedDate()).isEqualTo(testPaymentDetails.get(i).getCreatedDate());
			assertThat(paymentDetails.get(i).getDiscount()).isEqualTo(testPaymentDetails.get(i).getDiscount());
		}
	}

	@Test
	void given_paymentDetail_when_updatePaymentDetail_will_return_paymentDetail() {
		PaymentDetail paymentDetail = createTesPaymentDetail(1);

		given(paymentDetailRepository.existsById(paymentDetail.getId())).willReturn(true);
		given(paymentDetailRepository.save(paymentDetail)).willReturn(paymentDetail);

		PaymentDetail testPaymentDetail = paymentDetailService.updatePaymentDetail(paymentDetail);

		assertEquals(paymentDetail, testPaymentDetail);
		assertThat(testPaymentDetail).isNotNull();
		assertThat(testPaymentDetail.getId()).isNotNull().isEqualTo(paymentDetail.getId());
		assertThat(testPaymentDetail.getAmount()).isEqualTo(paymentDetail.getAmount());
		assertThat(testPaymentDetail.getClassified()).isEqualTo(paymentDetail.getClassified());
		assertThat(testPaymentDetail.getCreatedDate()).isEqualTo(paymentDetail.getCreatedDate());
		assertThat(testPaymentDetail.getDiscount()).isEqualTo(paymentDetail.getDiscount());
	}

	@Test
	void given_paymentDetail_when_updatePaymentDetail_will_throw_NotFoundException() {
		PaymentDetail paymentDetail = createTesPaymentDetail(1);
		int id = paymentDetail.getId();
		String message = String.format("%d id'li ödeme detayı bulunamadı!", id);

		given(paymentDetailRepository.existsById(id)).willReturn(false);

		NotFoundException actual = assertThrows(NotFoundException.class, () -> paymentDetailService.updatePaymentDetail(paymentDetail));

		assertThat(actual.getMessage()).isEqualTo(message);
	}

	@Test
	void given_paymentDetailId_when_findById_will_return_paymentDetail() {
		PaymentDetail paymentDetail = createTesPaymentDetail(1);
		int id = paymentDetail.getId();

		given(paymentDetailRepository.findById(id)).willReturn(Optional.of(paymentDetail));

		PaymentDetail testPaymentDetail = paymentDetailService.findById(id);

		assertEquals(paymentDetail, testPaymentDetail);
		assertThat(testPaymentDetail).isNotNull();
		assertThat(testPaymentDetail.getId()).isNotNull().isEqualTo(paymentDetail.getId());
		assertThat(testPaymentDetail.getAmount()).isEqualTo(paymentDetail.getAmount());
		assertThat(testPaymentDetail.getClassified()).isEqualTo(paymentDetail.getClassified());
		assertThat(testPaymentDetail.getCreatedDate()).isEqualTo(paymentDetail.getCreatedDate());
		assertThat(testPaymentDetail.getDiscount()).isEqualTo(paymentDetail.getDiscount());
	}

	@Test
	void given_paymentDetailId_when_findById_will_throw_NotFoundException() {
		PaymentDetail paymentDetail = createTesPaymentDetail(1);
		int id = paymentDetail.getId();
		String message = String.format("%d id'li ödeme detayı bulunamadı!", id);

		NotFoundException actual = assertThrows(NotFoundException.class, () -> paymentDetailService.findById(id));

		assertThat(actual.getMessage()).isEqualTo(message);
	}

	@Test
	void given_idList_when_deleteAll_then_deletedObjectCount() {
		List<Integer> idList = new ArrayList<>();
		idList.add(1);
		idList.add(2);
		given(paymentDetailRepository.deleteAll(idList)).willReturn(idList.size());
		int deletedObjectCount = paymentDetailService.deletePaymentDetails(idList);
		assertThat(deletedObjectCount).isEqualTo(idList.size());
	}

	private PaymentDetail createTesPaymentDetail(int id) {
		PaymentDetail paymentDetail = new PaymentDetail(id, null, new Date(), 150.0, 15);

		return paymentDetail;
	}

	private List<PaymentDetail> createTesPaymentDetailList(int id) {
		List<PaymentDetail> paymentDetails = new ArrayList<PaymentDetail>();
		for (int i = 0; i < id; i++) {
			paymentDetails.add(createTesPaymentDetail(i));
		}
		return paymentDetails;
	}
}
