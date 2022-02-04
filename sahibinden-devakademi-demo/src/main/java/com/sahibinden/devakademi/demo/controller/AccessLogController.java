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

import com.sahibinden.devakademi.demo.model.AccessLog;
import com.sahibinden.devakademi.demo.response.PageableResponse;
import com.sahibinden.devakademi.demo.response.ResponseAccessLog;
import com.sahibinden.devakademi.demo.response.ResponseListAccessLog;
import com.sahibinden.devakademi.demo.service.AccessLogService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/access-logs")
public class AccessLogController {

	private AccessLogService accessLogService;

	@GetMapping
	public ResponseEntity<ResponseListAccessLog> getAccessLogs(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "20") Integer pageSize) {

		PageableResponse<AccessLog> accessLogResponse = accessLogService.getAccessLogsByPage(pageNo, pageSize);
		ResponseListAccessLog responseListAccessLog = new ResponseListAccessLog(accessLogResponse.getItemList());

		return new ResponseEntity<>(responseListAccessLog, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseAccessLog> getAccessLogById(@PathVariable("id") int id) {
		ResponseAccessLog responseAccessLog = new ResponseAccessLog();
		responseAccessLog.setData(accessLogService.findById(id));

		return new ResponseEntity<>(responseAccessLog, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<ResponseListAccessLog> addAccessLogs(@RequestBody ResponseListAccessLog responseListAccessLog) {
		ResponseListAccessLog returnResponseListAccessLog = new ResponseListAccessLog();
		returnResponseListAccessLog.setData(accessLogService.saveAccessLogs(responseListAccessLog.getData()));

		return new ResponseEntity<>(returnResponseListAccessLog, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseAccessLog> updateAccessLog(@RequestBody ResponseAccessLog responseAccessLog) {
		ResponseAccessLog returnResponseAccessLog = new ResponseAccessLog();
		returnResponseAccessLog.setData(accessLogService.updateAccessLog(responseAccessLog.getData()));

		return new ResponseEntity<>(returnResponseAccessLog, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{idList}")
	public int deleteAccessLogs(@PathVariable("idList") List<Integer> idList) {
		return accessLogService.deleteAccessLogs(idList);
	}
}
