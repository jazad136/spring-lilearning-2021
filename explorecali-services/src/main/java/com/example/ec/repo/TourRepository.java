package com.example.ec.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.ec.domain.Tour;

public interface TourRepository extends CrudRepository<Tour, Integer>{
	List<Tour> findByTourPackageCode(@Param("code") String code);
}
