package com.sahibinden.devakademi.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sahibinden.devakademi.demo.model.PaymentDetail;

@Repository
public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Integer> {

	@Modifying
	@Query(value = "DELETE p from payment_detail p WHERE p.id in :idList", nativeQuery = true)
	int deleteAll(@Param("idList") List<Integer> idList);
}
