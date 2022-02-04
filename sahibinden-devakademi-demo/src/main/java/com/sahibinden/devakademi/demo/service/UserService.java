package com.sahibinden.devakademi.demo.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sahibinden.devakademi.demo.exception.NotFoundException;
import com.sahibinden.devakademi.demo.exception.PageNotFoundException;
import com.sahibinden.devakademi.demo.model.User;
import com.sahibinden.devakademi.demo.repository.UserRepository;
import com.sahibinden.devakademi.demo.response.PageableResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

	private UserRepository userRepository;

	public PageableResponse<User> getAccessLogsByPage(Integer pageNo, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		if (!pageable.isUnpaged()) {
			return new PageableResponse<>(userRepository.findAll(pageable));
		} else {
			throw new PageNotFoundException("Sayfa bilgisi bulunmamaktadır!");
		}
	}

	public User findById(int id) {

		return userRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("%d id'li kullanıcı bulunamadı!", id)));
	}

	@Transactional
	public List<User> saveUsers(List<User> users) {
		List<User> returnUsersValue = userRepository.saveAll(users);
		log.info("Kullanıcılar başarılı bir şekilde eklenmiştir.");
		return returnUsersValue;
	}

	@Transactional
	public User updateUser(User user) {
		int id = user.getId();
		if (userRepository.existsById(id)) {
			User returnUserValue = userRepository.save(user);
			log.info(String.format("%d id'li kullanıcı başarılı bir şekilde güncellendi.", id));
			return returnUserValue;
		}
		throw new NotFoundException(String.format("%d id'li kullanıcı bulunamadı!", id));
	}

	@Transactional
	public int deleteUsers(List<Integer> idList) {
		int deletedObjectCount = userRepository.deleteAll(idList);
		log.info(String.format("%d adet kullanıcı silindmiştir.", deletedObjectCount));
		return deletedObjectCount;
	}
}
