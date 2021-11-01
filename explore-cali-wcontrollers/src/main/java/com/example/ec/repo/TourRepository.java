package com.example.ec.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.ec.domain.Tour;

public interface TourRepository extends PagingAndSortingRepository<Tour, Integer>{
	Page<Tour> findByTourPackageCode(@Param("code") String code, Pageable pageable);

	@Override
	@RestResource(exported = false)
	<S extends Tour> S save(S entity);

	@Override
	@RestResource(exported = false)
	<S extends Tour> Iterable<S> saveAll(Iterable<S> entities);

	@Override
	@RestResource(exported = false)
	void deleteAllById(Iterable<? extends Integer> ids);

	@Override
	@RestResource(exported = false)
	void deleteAll(Iterable<? extends Tour> entities);

	@Override
	@RestResource(exported = false)
	void deleteAll();

}
