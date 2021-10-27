package com.example.ec.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.ec.domain.TourRating;
import com.example.ec.domain.TourRatingPk;

public interface TourRatingRepository extends CrudRepository<TourRating, TourRatingPk> {
	List<TourRating> findByPkTourId(Integer tourId);
	
	Optional<TourRating> findByPkTourIdAndPkCustomerId(Integer tourId, Integer customerId);
	
	Page<TourRating> findByPkTourId(Integer tourId, Pageable pageable);
}
