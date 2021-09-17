package com.example.ec.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.ec.domain.Tour;

public interface TourRepository extends CrudRepository<Tour, Integer>{
	
}
