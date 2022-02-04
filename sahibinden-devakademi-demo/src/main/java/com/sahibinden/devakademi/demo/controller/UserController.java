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

import com.sahibinden.devakademi.demo.model.User;
import com.sahibinden.devakademi.demo.response.PageableResponse;
import com.sahibinden.devakademi.demo.response.ResponseListUser;
import com.sahibinden.devakademi.demo.response.ResponseUser;
import com.sahibinden.devakademi.demo.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

	private UserService userService;

	@GetMapping
	public ResponseEntity<ResponseListUser> getUser(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "20") Integer pageSize) {

		PageableResponse<User> returnAccessLogResponse = userService.getAccessLogsByPage(pageNo, pageSize);
		ResponseListUser responseListUser = new ResponseListUser(returnAccessLogResponse.getItemList());

		return new ResponseEntity<>(responseListUser, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseUser> getUserById(@PathVariable("id") int id) {
		ResponseUser returnResponseUser = new ResponseUser();
		returnResponseUser.setData(userService.findById(id));

		return new ResponseEntity<>(returnResponseUser, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<ResponseListUser> addUsers(@RequestBody ResponseListUser responseListUser) {
		ResponseListUser returnResponseListUser = new ResponseListUser();
		returnResponseListUser.setData(userService.saveUsers(responseListUser.getData()));

		return new ResponseEntity<>(returnResponseListUser, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseUser> updateUser(@RequestBody ResponseUser responseUser) {
		ResponseUser returnResponseUser = new ResponseUser();
		returnResponseUser.setData(userService.updateUser(responseUser.getData()));

		return new ResponseEntity<>(returnResponseUser, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{idList}")
	public int deleteUsers(@PathVariable("idList") List<Integer> idList) {
		return userService.deleteUsers(idList);
	}
}
