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
import com.sahibinden.devakademi.demo.repository.AccessLogRepository;

public class AccessLogServiceTest {

	private AccessLogService accessLogService;

	@Mock
	private AccessLogRepository accessLogRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		accessLogService = new AccessLogService(accessLogRepository);
	}

	@Test
	void given_accessLog_when_addAccessLogs_will_return_accessLogs() {
		List<AccessLog> accessLogs = createTesAccessLogList(3);
		given(accessLogRepository.saveAll(accessLogs)).willReturn(accessLogs);

		List<AccessLog> testAccessLogs = accessLogService.saveAccessLogs(accessLogs);

		for (int i = 0; i < accessLogs.size(); i++) {
			assertEquals(accessLogs.get(i), testAccessLogs.get(i));
			assertThat(accessLogs.get(i).getId()).isNotNull().isEqualTo(testAccessLogs.get(i).getId());
			assertThat(accessLogs.get(i).getCreatedDate()).isEqualTo(testAccessLogs.get(i).getCreatedDate());
			assertThat(accessLogs.get(i).getEndpoint()).isNotNull().isEqualTo(testAccessLogs.get(i).getEndpoint());
			assertThat(accessLogs.get(i).getUser()).isEqualTo(testAccessLogs.get(i).getUser());
		}
	}

	@Test
	void given_accessLog_when_updateAccessLog_will_return_accessLog() {
		AccessLog accessLog = createTesAccessLog(1);

		given(accessLogRepository.existsById(accessLog.getId())).willReturn(true);
		given(accessLogRepository.save(accessLog)).willReturn(accessLog);

		AccessLog testAccessLog = accessLogService.updateAccessLog(accessLog);

		assertEquals(accessLog, testAccessLog);
		assertThat(testAccessLog).isNotNull();
		assertThat(testAccessLog.getId()).isNotNull().isEqualTo(accessLog.getId());
		assertThat(testAccessLog.getCreatedDate()).isEqualTo(accessLog.getCreatedDate());
		assertThat(testAccessLog.getEndpoint()).isNotNull().isEqualTo(accessLog.getEndpoint());
		assertThat(testAccessLog.getUser()).isEqualTo(accessLog.getUser());
	}

	@Test
	void given_accessLog_when_updateAccessLog_will_throw_NotFoundException() {
		AccessLog accessLog = createTesAccessLog(1);
		int id = accessLog.getId();
		String message = String.format("%d id'li erişim logu bulunamadı!", id);

		given(accessLogRepository.existsById(id)).willReturn(false);

		NotFoundException actual = assertThrows(NotFoundException.class, () -> accessLogService.updateAccessLog(accessLog));

		assertThat(actual.getMessage()).isEqualTo(message);
	}

	@Test
	void given_accessLogId_when_findById_will_return_accessLog() {
		AccessLog accessLog = createTesAccessLog(1);
		int id = accessLog.getId();

		given(accessLogRepository.findById(id)).willReturn(Optional.of(accessLog));

		AccessLog testAccessLog = accessLogService.findById(id);

		assertEquals(accessLog, testAccessLog);
		assertThat(testAccessLog).isNotNull();
		assertThat(testAccessLog.getId()).isNotNull().isEqualTo(accessLog.getId());
		assertThat(testAccessLog.getCreatedDate()).isEqualTo(accessLog.getCreatedDate());
		assertThat(testAccessLog.getEndpoint()).isNotNull().isEqualTo(accessLog.getEndpoint());
		assertThat(testAccessLog.getUser()).isEqualTo(accessLog.getUser());
	}

	@Test
	void given_accessLogId_when_findById_will_throw_NotFoundException() {
		AccessLog accessLog = createTesAccessLog(1);
		int id = accessLog.getId();
		String message = String.format("%d id'li erişim logu bulunamadı!", id);

		NotFoundException actual = assertThrows(NotFoundException.class, () -> accessLogService.findById(id));

		assertThat(actual.getMessage()).isEqualTo(message);
	}

	@Test
	void given_idList_when_deleteAll_then_deletedObjectCount() {
		List<Integer> idList = new ArrayList<>();
		idList.add(1);
		idList.add(2);
		given(accessLogRepository.deleteAll(idList)).willReturn(idList.size());
		int deletedObjectCount = accessLogService.deleteAccessLogs(idList);
		assertThat(deletedObjectCount).isEqualTo(idList.size());
	}

	private AccessLog createTesAccessLog(int id) {
		User user = new User(1, "arda", "uslu", "active", null, new ArrayList<Classified>());
		AccessLog accessLog = new AccessLog(id, null, "/v1/api/users/38797", 123564894);
		user.setAccessLog(accessLog);
		accessLog.setUser(user);
		return accessLog;
	}

	private List<AccessLog> createTesAccessLogList(int id) {
		List<AccessLog> accessLogs = new ArrayList<AccessLog>();
		for (int i = 0; i < id; i++) {
			accessLogs.add(createTesAccessLog(i));
		}
		return accessLogs;
	}
}
