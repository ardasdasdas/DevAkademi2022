package com.sahibinden.devakademi.demo.response;

import com.sahibinden.devakademi.demo.model.PaymentDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponsePaymentDetail {

	PaymentDetail data;
}
