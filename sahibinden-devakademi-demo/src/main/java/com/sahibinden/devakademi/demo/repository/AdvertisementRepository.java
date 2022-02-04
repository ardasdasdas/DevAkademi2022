package com.sahibinden.devakademi.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sahibinden.devakademi.demo.model.Advertisement;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {

	@Query("SELECT a FROM Advertisement a WHERE a.category LIKE %?1%")
	Page<Advertisement> searchAdvertisement(String searchParam, Pageable pageable);

	@Modifying
	@Query(value = "DELETE a from advertisement a WHERE a.id in :idList", nativeQuery = true)
	int deleteAll(@Param("idList") List<Integer> idList);
}
