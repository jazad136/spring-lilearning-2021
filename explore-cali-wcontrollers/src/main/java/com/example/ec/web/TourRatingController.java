package com.example.ec.web;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ec.domain.Tour;
import com.example.ec.domain.TourRating;
import com.example.ec.domain.TourRatingPk;
import com.example.ec.repo.TourRatingRepository;
import com.example.ec.repo.TourRepository;

@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {
	private TourRatingRepository tourRatingRepository;
	private TourRepository tourRepository;
	
	@Autowired
    public TourRatingController(TourRatingRepository tourRatingRepository, TourRepository tourRepository) {
        this.tourRatingRepository = tourRatingRepository;
        this.tourRepository = tourRepository;
    }
	
	protected TourRatingController() { }
	
	/**
	 * Create the tour rating
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createTourRating(
			@PathVariable(value="tourId") int tourId, 
			@RequestBody @Validated RatingDto ratingDto) 
	{ 
		Tour tour = verifyTour(tourId);
		TourRatingPk tourRatingPk = new TourRatingPk(
				tour, ratingDto.getCustomerId());
		
		tourRatingRepository.save(new TourRating(
				tourRatingPk, ratingDto.getScore(), ratingDto.getComment()));
		
	}

	/* helper method to 
	/* verify that a tour exists, and throw a NoSuchElementException if it doesn't */
	private Tour verifyTour(int tourId) {
		return tourRepository.findById(tourId).orElseThrow(() -> 
			new NoSuchElementException("Tour does not exist " + tourId));
	}
	
	

	/**
	 * Get all ratings for a specific tour, using a tourId
	 */
	@GetMapping
	public Page<RatingDto> getAllRatingsForTour(@PathVariable(value = "tourId") int tourId, Pageable pageable) { 
    	verifyTour(tourId);
    	Page<TourRating> ratings = tourRatingRepository.findByPkTourId(tourId, pageable);
    	return new PageImpl<>(
			ratings.get().map(RatingDto::new).collect(Collectors.toList())
			,pageable
			,ratings.getTotalElements()
    	);
    }
	
//	@GetMapping
//	public List<RatingDto> getAllRatingsForTour(@PathVariable(value = "tourId") int tourId) { 
//    	verifyTour(tourId);
//    	return tourRatingRepository.findByPkTourId(tourId).stream()
//    			.map(RatingDto::new).collect(Collectors.toList());
//    }
    
	/**
	 * Get the average of all ratings for the specific tour.
	 * @param tourId
	 * @return
	 */
	@GetMapping(path="/average")
    public Map<String, Double> getAverage(@PathVariable(value = "tourId") int tourId) { 
    	verifyTour(tourId);
    	return Map.of("average", tourRatingRepository.findByPkTourId(tourId).stream()
    			.mapToInt(TourRating::getScore).average()
    			.orElseThrow(() -> 
    			new NoSuchElementException("Tour has no Ratings")));
    }
	
	/** Get the tour rating from the repository, and throw an exception if not found.*/
	private TourRating verifyTourRating(int tourId, int customerId) throws NoSuchElementException {
		return tourRatingRepository.findByPkTourIdAndPkCustomerId(tourId, customerId)
				.orElseThrow(() -> new NoSuchElementException("Tour-Rating pair for request("
						+ tourId + " for customer " + customerId));
	}
	
	/** 
	 * Updates all attributes of the rating (ratingDto score and comment and customerId) 
	 * of the rating identified by tourId and customerId. Return an updated
	 * ratingDto with an updated score and comment and customerId.
	 */
	@PutMapping
	public RatingDto updateWithPut(
			@PathVariable(value="tourId") int tourId, 
			@RequestBody @Validated RatingDto ratingDto
	) { 
		TourRating rating = verifyTourRating(tourId, ratingDto.getCustomerId());
		rating.setScore(ratingDto.getScore());
		rating.setComment(ratingDto.getComment());
		return new RatingDto(tourRatingRepository.save(rating));
	}
	
	@PatchMapping
	public RatingDto updateWithPatch(
			@PathVariable(value="tourId") int tourId, 
			@RequestBody @Validated RatingDto ratingDto) 
	{ 
		TourRating rating = verifyTourRating(tourId, ratingDto.getCustomerId()); 
		if(ratingDto.getScore() != null) { 
			rating.setScore(ratingDto.getScore());
		}
		if(ratingDto.getComment() != null) { 
			rating.setComment(ratingDto.getComment());
		}
		return new RatingDto(tourRatingRepository.save(rating));
	}
	@DeleteMapping(path = "/{customerId}")
	public void delete(
			@PathVariable(value="tourId") int tourId, 
			@PathVariable(value="customerId") int customerId) 
	{
		TourRating tourRating = verifyTourRating(tourId, customerId);
		tourRatingRepository.delete(tourRating);	
	}
	
	/** Handle the NoSuchElementException if it's ever thrown by returning a 
	 * NOT_FOUND (404) status*/
	@ResponseStatus(HttpStatus.NOT_FOUND)
//	@ExceptionHandler(NoSuchElementException.class)
	@ExceptionHandler(NoSuchElementException.class)
	
	public String return400(NoSuchElementException ex) { 
		return ex.getMessage();
	}
	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public String return406ForBadValue(MethodArgumentNotValidException ex) { 
//		return ex.getMessage();
//	}
	
}
