package com.example.ec.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.ec.domain.Tour;

/** 
 * TourRepository implementation saved from previous implementations of ExploreCali
 * @author Mary Ellen Bowman, with syntax and stylistic additions by Jonathan A. Saddler
 *
 */
public interface TourRepository extends PagingAndSortingRepository<Tour, Integer> {
	/** Lookup a page of tours associated with a tour package. */
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
