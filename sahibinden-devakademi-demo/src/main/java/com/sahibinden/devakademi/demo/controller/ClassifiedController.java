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

import com.sahibinden.devakademi.demo.model.Classified;
import com.sahibinden.devakademi.demo.response.PageableResponse;
import com.sahibinden.devakademi.demo.response.ResponseClassified;
import com.sahibinden.devakademi.demo.response.ResponseListClassified;
import com.sahibinden.devakademi.demo.service.ClassifiedService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/classifieds")
public class ClassifiedController {

	private ClassifiedService classifiedService;

	@GetMapping
	public ResponseEntity<ResponseListClassified> getClassifieds(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "20") Integer pageSize) {

		PageableResponse<Classified> accessLogResponse = classifiedService.getAccessLogsByPage(pageNo, pageSize);
		ResponseListClassified responseListClassified = new ResponseListClassified(accessLogResponse.getItemList());

		return new ResponseEntity<>(responseListClassified, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseClassified> getClassifiedById(@PathVariable("id") int id) {
		ResponseClassified responseClassified = new ResponseClassified();
		responseClassified.setData(classifiedService.findById(id));

		return new ResponseEntity<>(responseClassified, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<ResponseListClassified> addClassifieds(@RequestBody ResponseListClassified responseListClassified) {
		ResponseListClassified returnResponseListClassified = new ResponseListClassified();
		returnResponseListClassified.setData(classifiedService.saveClassifieds(responseListClassified.getData()));

		return new ResponseEntity<>(returnResponseListClassified, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseClassified> updateClassified(@RequestBody ResponseClassified responseClassified) {
		ResponseClassified returnResponseClassified = new ResponseClassified();
		returnResponseClassified.setData(classifiedService.updateClassified(responseClassified.getData()));

		return new ResponseEntity<>(returnResponseClassified, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{idList}")
	public int deleteClassifieds(@PathVariable("idList") List<Integer> idList) {
		return classifiedService.deleteClassified(idList);
	}
}
