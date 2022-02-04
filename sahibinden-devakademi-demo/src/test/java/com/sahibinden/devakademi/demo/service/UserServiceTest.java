package com.sahibinden.devakademi.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sahibinden.devakademi.demo.exception.NotFoundException;
import com.sahibinden.devakademi.demo.model.AccessLog;
import com.sahibinden.devakademi.demo.model.Classified;
import com.sahibinden.devakademi.demo.model.User;
import com.sahibinden.devakademi.demo.repository.UserRepository;

public class UserServiceTest {

	private UserService userServiceService;

	@Mock
	private UserRepository userRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		userServiceService = new UserService(userRepository);
	}

	@Test
	void given_users_when_addUsers_will_return_users() {
		List<User> users = createTesUserList(3);
		given(userRepository.saveAll(users)).willReturn(users);

		List<User> testUsers = userServiceService.saveUsers(users);

		for (int i = 0; i < users.size(); i++) {
			assertEquals(users.get(i), testUsers.get(i));
			assertThat(users.get(i).getId()).isNotNull().isEqualTo(testUsers.get(i).getId());
			assertThat(users.get(i).getAccessLog()).isEqualTo(testUsers.get(i).getAccessLog());
			assertThat(users.get(i).getClassifieds()).isNotNull().isEqualTo(testUsers.get(i).getClassifieds());
			assertThat(users.get(i).getFirstName()).isEqualTo(testUsers.get(i).getFirstName());
			assertThat(users.get(i).getLastName()).isEqualTo(testUsers.get(i).getLastName());
			assertThat(users.get(i).getStatus()).isEqualTo(testUsers.get(i).getStatus());
		}
	}

	@Test
	void given_user_when_updateUser_will_return_user() {
		User user = createTesUser(1);

		given(userRepository.existsById(user.getId())).willReturn(true);
		given(userRepository.save(user)).willReturn(user);

		User testUser = userServiceService.updateUser(user);

		assertEquals(user, testUser);
		assertThat(testUser).isNotNull();
		assertThat(testUser.getId()).isNotNull().isEqualTo(user.getId());
		assertThat(testUser.getAccessLog()).isEqualTo(user.getAccessLog());
		assertThat(testUser.getClassifieds()).isNotNull().isEqualTo(user.getClassifieds());
		assertThat(testUser.getFirstName()).isEqualTo(user.getFirstName());
		assertThat(testUser.getLastName()).isEqualTo(user.getLastName());
		assertThat(testUser.getStatus()).isEqualTo(user.getStatus());
	}

	@Test
	void given_user_when_updateUser_will_throw_NotFoundException() {
		User user = createTesUser(1);
		int id = user.getId();
		String message = String.format("%d id'li kullanıcı bulunamadı!", id);

		given(userRepository.existsById(id)).willReturn(false);

		NotFoundException actual = assertThrows(NotFoundException.class, () -> userServiceService.updateUser(user));

		assertThat(actual.getMessage()).isEqualTo(message);
	}

	@Test
	void given_userId_when_findById_will_return_user() {
		User user = createTesUser(1);
		int id = user.getId();

		given(userRepository.findById(id)).willReturn(Optional.of(user));

		User testUser = userServiceService.findById(id);

		assertEquals(user, testUser);
		assertThat(testUser).isNotNull();
		assertThat(testUser.getId()).isNotNull().isEqualTo(user.getId());
		assertThat(testUser.getAccessLog()).isEqualTo(user.getAccessLog());
		assertThat(testUser.getClassifieds()).isNotNull().isEqualTo(user.getClassifieds());
		assertThat(testUser.getFirstName()).isEqualTo(user.getFirstName());
		assertThat(testUser.getLastName()).isEqualTo(user.getLastName());
		assertThat(testUser.getStatus()).isEqualTo(user.getStatus());
	}

	@Test
	void given_userId_when_findById_will_throw_NotFoundException() {
		User user = createTesUser(1);
		int id = user.getId();
		String message = String.format("%d id'li kullanıcı bulunamadı!", id);

		NotFoundException actual = assertThrows(NotFoundException.class, () -> userServiceService.findById(id));

		assertThat(actual.getMessage()).isEqualTo(message);
	}

	@Test
	void given_idList_when_deleteAll_then_deletedObjectCount() {
		List<Integer> idList = new ArrayList<>();
		idList.add(1);
		idList.add(2);
		given(userRepository.deleteAll(idList)).willReturn(idList.size());
		int deletedObjectCount = userServiceService.deleteUsers(idList);
		assertThat(deletedObjectCount).isEqualTo(idList.size());
	}

	private User createTesUser(int id) {
		User user = new User(id, "arda", "uslu", "active", null, new ArrayList<Classified>());
		AccessLog accessLog = new AccessLog(id, null, "/v1/api/users/38797", 123564894);
		user.setAccessLog(accessLog);
		accessLog.setUser(user);
		return user;
	}

	private List<User> createTesUserList(int id) {
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < id; i++) {
			users.add(createTesUser(i));
		}
		return users;
	}

}
