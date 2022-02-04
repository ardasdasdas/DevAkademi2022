package com.sahibinden.devakademi.demo.response;

import java.util.List;

import com.sahibinden.devakademi.demo.model.AccessLog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseListAccessLog {

	List<AccessLog> data;
}
