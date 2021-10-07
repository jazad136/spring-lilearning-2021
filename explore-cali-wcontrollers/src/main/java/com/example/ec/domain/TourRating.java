package com.example.ec.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class TourRating {
	@EmbeddedId
	private TourRatingPk pk;
	
	@Column(nullable = false)
	private Integer score;
	
	@Column
	private String comment;
	
	public TourRating(TourRatingPk pk, Integer score, String comment) { 
		this.pk = pk;
		this.score = score;
		this.comment = comment;
	}
	
	protected TourRating() { }
	
	@Override
    public String toString() {
        return "TourRating{" +
                "pk=" + pk +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(pk, score, comment);
    }
	
	
}
