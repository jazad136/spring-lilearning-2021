package com.example.ec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ec.domain.Difficulty;
import com.example.ec.domain.Region;
import com.example.ec.domain.Tour;
import com.example.ec.domain.TourPackage;
import com.example.ec.repo.TourPackageRepository;
import com.example.ec.repo.TourRepository;

@Service
public class TourService {
	private TourRepository tourRepository;
	private TourPackageRepository tourPackageRepository;
	
	@Autowired
	public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) { 
		this.tourPackageRepository = tourPackageRepository;
		this.tourRepository = tourRepository;
	}
	
	/**
     * Create a new Tour Object and persist it to the Database.
     *
     * @param title title
     * @param description description
     * @param blurb blurb
     * @param price price
     * @param duration duration
     * @param bullets comma-separated bullets
     * @param keywords keywords
     * @param tourPackageName tour package name
     * @param difficulty difficulty
     * @param region region
     * @return Tour Entity
     */
	public Tour createTour(
			String title, String description, String blurb, 
			Integer price, String duration, String bullets, 
			String keywords, String tourPackageName, Difficulty difficulty, Region region) {
//			String title, String tourPackageName, Map<String, String> details) { // for MongoDB implementation 
		TourPackage tourPackage = null;
		
		tourPackage = tourPackageRepository
				.findByName(tourPackageName)
				.orElseThrow(() -> new RuntimeException("Tour package does not exist " + tourPackageName));
		
		return tourRepository.save(new Tour(title, description, blurb, 
				price, duration, bullets, 
				keywords, tourPackage, difficulty, region));
//		return tourRepository.save(new Tour(title, tourPackage, details)); // again for mongoDB implementation.
	
	}
	
	public long total() { return tourRepository.count(); } 
	
}
