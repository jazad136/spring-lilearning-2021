package com.example.ec.web;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;

import com.example.ec.ExplorecaliApplication;
import com.example.ec.domain.Tour;
import com.example.ec.domain.TourRating;
import com.example.ec.service.TourRatingService;


/**
 *
 * Invoke the Controller methods via HTTP.
 * Do not invoke the tourRatingService methods, use Mock instead
 * 
 * Created by Mary Ellen Bowman.
 * Syntax changes for JUnit5 by Jonathan Saddler
 * 
 * 
 * Note These tests test the API calls to the methods that need to be called at 
 * URI requests. They really don't test the functionality of the method calls themselves
 * just that the web server layer is doing it's job converting and returning the right data. 
 * 
 * TourRating and Tour are mocks. 
 * 
 * TourRatingService is a Spring Mock Bean. It will also have mocks injected into it, 
 * although tourRatingService is not itself a mock.  
 * 
 */

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = ExplorecaliApplication.class)
@AutoConfigureMockMvc
public class TourRatingControllerTest {

    //These Tour and rating id's do not already exist in the db
    private static final int TOUR_ID = 999;
    private static final int CUSTOMER_ID = 1000;
    private static final int SCORE = 3;
    private static final String COMMENT = "comment";
    private static final String TOUR_RATINGS_URL = "/tours/" + TOUR_ID + "/ratings";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JwtRequestHelper jwtRequestHelper;

    @MockBean
    private TourRatingService serviceMock;

    @Mock
    private TourRating tourRatingMock;

    @Mock
    private Tour tourMock;

    private RatingDto ratingDto = new RatingDto(SCORE, COMMENT,CUSTOMER_ID);

    @BeforeEach
    public void setupReturnValuesOfMockMethods() {
        when(tourRatingMock.getComment()).thenReturn(COMMENT);
        when(tourRatingMock.getScore()).thenReturn(SCORE);
        when(tourRatingMock.getCustomerId()).thenReturn(CUSTOMER_ID);
        when(tourRatingMock.getTour()).thenReturn(tourMock);
        when(tourMock.getId()).thenReturn(TOUR_ID);
    }

    /**
     *  HTTP POST /tours/{tourId}/ratings
     */
    @Test
    public void createTourRating() throws Exception {
//        restTemplate
//                .postForEntity(TOUR_RATINGS_URL, ratingDto, Void.class);
    	restTemplate.exchange(TOUR_RATINGS_URL, HttpMethod.POST, 
    			new HttpEntity<>(ratingDto, jwtRequestHelper.withRole("ROLE_CSR")), Void.class);
        verify(this.serviceMock)
                .createNew(TOUR_ID, CUSTOMER_ID, SCORE, COMMENT);
    }

    /**
     *  HTTP DELETE /tours/{tourId}/ratings
     */
    // for security, we need to change things up here.
    @Test
    public void delete() throws Exception {
//        restTemplate.delete(TOUR_RATINGS_URL + "/" + CUSTOMER_ID);
    	
    	restTemplate.exchange(TOUR_RATINGS_URL + "/" + CUSTOMER_ID, HttpMethod.DELETE, 
    			new HttpEntity<>(jwtRequestHelper.withRole("ROLE_CSR")), Void.class);

        verify(serviceMock).delete(TOUR_ID, CUSTOMER_ID);
    }
    /**
     *  HTTP POST /tours/{tourId}/ratings/{score}?customers={ids..}
     */
    @Test
    public void createManyTourRatings() throws Exception {
//        restTemplate
//                .postForEntity(TOUR_RATINGS_URL + "/" + SCORE + "?customers=" + CUSTOMER_ID, ratingDto, Void.class);
    	restTemplate.exchange(TOUR_RATINGS_URL + "/" + SCORE + "?customers=" + CUSTOMER_ID, 
    			HttpMethod.POST, new HttpEntity<>(ratingDto, jwtRequestHelper.withRole("ROLE_CSR")), Void.class);
        verify(serviceMock)
                .rateMany(TOUR_ID, SCORE, new Integer[] {CUSTOMER_ID});
    }

    /**
     *  HTTP GET /tours/{tourId}/ratings
     */
    @Test
    public void getAllRatingsForTour() throws Exception {
        when(serviceMock.lookupRatings(anyInt(),any(Pageable.class)))
                .thenReturn(new PageImpl(Arrays.asList(tourRatingMock),
                        PageRequest.of(0,10),1));

        ResponseEntity<String> response = restTemplate.getForEntity(TOUR_RATINGS_URL,String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        verify(serviceMock).lookupRatings(anyInt(), any(Pageable.class));
    }

    /**
     *  HTTP GET /tours/{tourId}/ratings/average
     */
    @Test
    public void getAverage() throws Exception {
        when(serviceMock.getAverageScore(TOUR_ID)).thenReturn(3.2);

        ResponseEntity<String> response = restTemplate
                .getForEntity(TOUR_RATINGS_URL + "/average", String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is("{\"average\":3.2}"));
    }
    @Test
    public void getAverage_TourNotFound() {
        when(serviceMock.getAverageScore(TOUR_ID))
                .thenThrow(NoSuchElementException.class);

        ResponseEntity<String> response = restTemplate.
                getForEntity(TOUR_RATINGS_URL + "/average", String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }


    /**
     *  HTTP PUT /tours/{tourId}/ratings
     */
    @Test
    public void updateWithPut() throws Exception {
        when(serviceMock.update(TOUR_ID, CUSTOMER_ID, SCORE, COMMENT))
                .thenReturn(tourRatingMock);

//        restTemplate.put(TOUR_RATINGS_URL, ratingDto);
        restTemplate.exchange(TOUR_RATINGS_URL, HttpMethod.PUT, new HttpEntity<>(ratingDto,jwtRequestHelper.withRole("ROLE_CSR")), Void.class);
        verify(serviceMock).update(TOUR_ID, CUSTOMER_ID, SCORE, COMMENT);
    }

    /**
     *  HTTP PATCH /tours/{tourId}/ratings
     */

    /**
     *  RestTemplate Patch only works if it uses httpclient. Method will only work if:
     *  1. Include dependency
     *      <dependency>
     *            <groupId>org.apache.httpcomponents</groupId>
     *           <artifactId>httpclient</artifactId>
     *           <version>4.4.1</version>
     *       </dependency>
     *  2. Attach httpclient
     *      restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
     */
    @Test
    public  void updateWithPatch() {
        when(serviceMock.updateSome(TOUR_ID, CUSTOMER_ID, SCORE, COMMENT))
                .thenReturn(tourRatingMock);
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
//        restTemplate.patchForObject(TOUR_RATINGS_URL, ratingDto, RatingDto.class);
        restTemplate.exchange(
        		TOUR_RATINGS_URL, 
        		HttpMethod.PATCH, 
        		new HttpEntity<>(ratingDto, jwtRequestHelper.withRole("ROLE_CSR")), Void.class);
        
        verify(serviceMock).updateSome(TOUR_ID, CUSTOMER_ID, SCORE, COMMENT);
    }

}