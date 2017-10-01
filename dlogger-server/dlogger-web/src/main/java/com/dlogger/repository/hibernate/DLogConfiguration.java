package com.dlogger.repository.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DLogConfiguration {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Integer timeToLive;

	public Long getId() { return id; }
	
	public Integer getTimeToLive() { return timeToLive; }
	public void setTimeToLive(Integer timeToLive) { this.timeToLive = timeToLive; }

	@Override
	public String toString() {
		return "DLogConfiguration [id=" + id + ", timeToLive=" + timeToLive + "]";
	}
	
	
    
}

