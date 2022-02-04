package com.sahibinden.devakademi.demo.response;

import java.util.List;

import com.sahibinden.devakademi.demo.model.Classified;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseListClassified {

	List<Classified> data;
}
