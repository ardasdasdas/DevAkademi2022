package com.sahibinden.devakademi.demo.response;

import java.util.List;

import com.sahibinden.devakademi.demo.model.Advertisement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseListAdvertisement {
	List<Advertisement> data;
}
