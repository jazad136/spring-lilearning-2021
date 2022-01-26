package com.example.ec.web;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.example.ec.Explorecali21Application;
import com.example.ec.domain.Tour;
import com.example.ec.domain.TourRating;
import com.example.ec.service.TourRatingService;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
/**
 * Invoke the Controller methods via HTTP.
 * Do not invoke the tourRatingService methods, use Mock instead
 * Created by Mary Ellen Bowman.
 *
 * Syntax changes for JUnit5 by Jonathan Saddler
 * 
 * Note These tests test the API calls to the methods that need to be called at 
 * URI requests. They really don't test the functionality of the method calls themselves
 * just that the web server layer is doing it's job converting and returning the right data. 
 * 
 * Here we need a way to take out the part of the java methods inside the controllers. 
 * We're going to use both Mockito and Spring testRestTemplate. 
 * 
 * TourRating and Tour are mocks. 
 * TourRatingService is a Spring Mock Bean. It will also have mocks injected into it, 
 * although tourRatingService is not itself a mock.  
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = Explorecali21Application.class)
//@AutoConfigureMockMvc
public class RatingControllerTest {
    private static final String RATINGS_URL = "/ratings";

    //These Tour and rating id's do not already exist in the db
    private static final int TOUR_ID = 999;
    private static final int RATING_ID = 555;
    private static final int CUSTOMER_ID = 1000;
    private static final int SCORE = 3;
    private static final String COMMENT = "comment";

    @MockBean
    private TourRatingService tourRatingServiceMock;

    @Mock
    private TourRating tourRatingMock;

    @Mock
    private Tour tourMock;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setupReturnValuesOfMockMethods() {
        when(tourRatingMock.getTour()).thenReturn(tourMock);
        when(tourMock.getId()).thenReturn(TOUR_ID);
        when(tourRatingMock.getComment()).thenReturn(COMMENT);
        when(tourRatingMock.getScore()).thenReturn(SCORE);
        when(tourRatingMock.getCustomerId()).thenReturn(CUSTOMER_ID);
    }


    /**
     *  HTTP GET /ratings
     */
    @Test
    public void getRatings() {
        when(tourRatingServiceMock.lookupAll())
                .thenReturn(
                        Arrays.asList(tourRatingMock, tourRatingMock, tourRatingMock));

        ResponseEntity<List> response = restTemplate
                .getForEntity(RATINGS_URL, List.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().size(), is(3));
    }

    /**
     *  HTTP GET /ratings/{id}
     */
    @Test
    public void getOne()  {

        when(tourRatingServiceMock.lookupRatingById(RATING_ID))
                .thenReturn(Optional.of(tourRatingMock));

        ResponseEntity<RatingDto> response = restTemplate
                .getForEntity(RATINGS_URL + "/" + RATING_ID, RatingDto.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getCustomerId(), is(CUSTOMER_ID));
        assertThat(response.getBody().getComment(), is(COMMENT));
        assertThat(response.getBody().getScore(), is(SCORE));
    }

    @Test
    public void getOne_notFound() {
        when(tourRatingServiceMock.lookupRatingById(RATING_ID))
                .thenReturn(Optional.empty());

        ResponseEntity<String> response =
                restTemplate.getForEntity(RATINGS_URL + "/" + RATING_ID, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(),
                containsString("Rating " + RATING_ID + " not found"));
    }
}