package com.example.ec.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.ec.domain.TourPackage;

public interface TourPackageRepository extends CrudRepository<TourPackage, String> {
	Optional<TourPackage> findByName(String name);
}
