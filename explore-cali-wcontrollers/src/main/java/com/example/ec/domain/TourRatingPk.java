package com.example.ec.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Tour Rating Primary Key containing a tour and customer id. 
 * 
 * Created by Mary Ellen Bowman with Syntactic Sugar by Jonathan A. Saddler. 
 * @author Mary Ellen Bowman
 * @author Jonathan A. Saddler
 *
 */
@Embeddable
public class TourRatingPk {
	@ManyToOne
	private Tour tour;
	
	@Column(insertable = false, updatable = false, nullable = false)
	private Integer customerId;
	
	public TourRatingPk() { }
	
	public Tour getTour() { return tour; } 
	
	public Integer getCustomerId() { return customerId; } 
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourRatingPk that = (TourRatingPk) o;

        if (!tour.equals(that.tour)) return false;
        return customerId.equals(that.customerId);

    }
	
	@Override
    public int hashCode() {
        int result = tour.hashCode();
        result = 31 * result + customerId.hashCode();
        return result;
    }
	
	@Override
    public String toString() {
        return "TourRatingPk{" +
                "tour=" + tour +
                ", customerId=" + customerId +
                '}';
    }
}
