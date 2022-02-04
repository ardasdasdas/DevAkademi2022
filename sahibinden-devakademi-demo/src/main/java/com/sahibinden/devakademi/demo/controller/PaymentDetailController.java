package com.sahibinden.devakademi.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sahibinden.devakademi.demo.model.PaymentDetail;
import com.sahibinden.devakademi.demo.response.PageableResponse;
import com.sahibinden.devakademi.demo.response.ResponseListPaymentDetail;
import com.sahibinden.devakademi.demo.response.ResponsePaymentDetail;
import com.sahibinden.devakademi.demo.service.PaymentDetailService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/payment-details")
public class PaymentDetailController {

	private PaymentDetailService paymentDetailService;

	@GetMapping
	public ResponseEntity<ResponseListPaymentDetail> getPaymentDetails(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "20") Integer pageSize) {

		PageableResponse<PaymentDetail> accessLogResponse = paymentDetailService.getAccessLogsByPage(pageNo, pageSize);
		ResponseListPaymentDetail responseListPaymentDetail = new ResponseListPaymentDetail(accessLogResponse.getItemList());

		return new ResponseEntity<>(responseListPaymentDetail, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponsePaymentDetail> getPaymentDetailById(@PathVariable("id") int id) {
		ResponsePaymentDetail responsePaymentDetail = new ResponsePaymentDetail();
		responsePaymentDetail.setData(paymentDetailService.findById(id));

		return new ResponseEntity<>(responsePaymentDetail, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<ResponseListPaymentDetail> addPaymentDetails(@RequestBody ResponseListPaymentDetail responseListPaymentDetail) {
		ResponseListPaymentDetail returnResponseListPaymentDetail = new ResponseListPaymentDetail();
		returnResponseListPaymentDetail.setData(paymentDetailService.savePaymentDetails(responseListPaymentDetail.getData()));

		return new ResponseEntity<>(returnResponseListPaymentDetail, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponsePaymentDetail> updatePaymentDetail(@RequestBody ResponsePaymentDetail responsePaymentDetail) {
		ResponsePaymentDetail returnResponsePaymentDetail = new ResponsePaymentDetail();
		returnResponsePaymentDetail.setData(paymentDetailService.updatePaymentDetail(responsePaymentDetail.getData()));

		return new ResponseEntity<>(returnResponsePaymentDetail, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{idList}")
	public int deletePaymentDetails(@PathVariable("idList") List<Integer> idList) {
		return paymentDetailService.deletePaymentDetails(idList);
	}
}
