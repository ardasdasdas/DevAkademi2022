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

import com.sahibinden.devakademi.demo.response.ResponseAdvertisement;
import com.sahibinden.devakademi.demo.response.ResponseListAdvertisement;
import com.sahibinden.devakademi.demo.service.AdvertisementService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/advertisements")
public class AdvertisementController {

	private AdvertisementService advertisementService;

	@GetMapping
	public ResponseEntity<ResponseListAdvertisement> searchAdvertisement(@RequestParam("category") String category,
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "20") Integer pageSize) {

		ResponseListAdvertisement responseAdvertisement = new ResponseListAdvertisement(
				advertisementService.searchAdvertisement(category, pageNo, pageSize).getItemList());

		return new ResponseEntity<>(responseAdvertisement, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<ResponseListAdvertisement> addAdvertisements(@RequestBody ResponseListAdvertisement responseListAdvertisement) {
		ResponseListAdvertisement returnResponseListAdvertisement = new ResponseListAdvertisement();
		returnResponseListAdvertisement.setData(advertisementService.saveAdvertisements(responseListAdvertisement.getData()));

		return new ResponseEntity<>(returnResponseListAdvertisement, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseAdvertisement> updateAdvertisement(@RequestBody ResponseAdvertisement responseAdvertisement) {
		ResponseAdvertisement returnResponseListAdvertisement = new ResponseAdvertisement();
		returnResponseListAdvertisement.setData(advertisementService.updateAdvertisement(responseAdvertisement.getData()));

		return new ResponseEntity<>(returnResponseListAdvertisement, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{idList}")
	public int deleteAdvertisements(@PathVariable("idList") List<Integer> idList) {
		return advertisementService.deleteAdvertisement(idList);
	}
}
