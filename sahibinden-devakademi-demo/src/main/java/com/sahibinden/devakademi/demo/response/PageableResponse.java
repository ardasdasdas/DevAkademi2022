package com.sahibinden.devakademi.demo.response;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageableResponse<E> {

	private List<E> itemList;
	private Integer currentPage;
	private Integer totalPages;
	private Long totalItems;

	public PageableResponse(Page<E> page) {
		this.currentPage = page.getNumber();
		this.totalItems = page.getTotalElements();
		this.totalPages = page.getTotalPages();
		this.itemList = page.getContent();
	}
}
