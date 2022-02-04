package com.sahibinden.devakademi.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sahibinden.devakademi.demo.model.AccessLog;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Integer> {

	@Modifying
	@Query(value = "DELETE a from access_log a WHERE a.id in :idList", nativeQuery = true)
	int deleteAll(@Param("idList") List<Integer> idList);

}
