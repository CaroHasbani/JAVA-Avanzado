package com.example.demo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "passenger")
@EntityListeners(AuditingEntityListener.class)
public class Passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "passenger_id")
	private Long passenger_id;

	
	@Column(name = "flightNumber")
	private String flightNumber;
	
	@Column(name = "Name")
	private String name;
	
	

	@OneToOne
	//@OneToOne(fetch = FetchType.LAZY, mappedBy = "flight_id")
    private Flight flight;
	
	
	
	public Passenger() {

	}

	public Long getPassenger_id() {
		return passenger_id;
	}

	public void setPassenger_id(Long passenger_id) {
		this.passenger_id = passenger_id;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Passenger(Long passenger_id,  String flightNumber, String name) {
		this.passenger_id = passenger_id;
		this.flightNumber = flightNumber;
		this.name = name;

	}
	
	@Override
    public String toString() {
        return "Passenger{" +
                "id=" + passenger_id +
                ", name=" + name +
                ", flightNumber=" + flightNumber +
               '}';
    }
}