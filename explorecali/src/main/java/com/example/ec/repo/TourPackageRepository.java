package com.example.ec.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.ec.domain.TourPackage;

public interface TourPackageRepository extends CrudRepository<TourPackage, String> {
	
}
