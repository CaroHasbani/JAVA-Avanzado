package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "plane")
@EntityListeners(AuditingEntityListener.class)
public class Plane {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "plane_id")
	private Long plane_id;

	
	@Column(name = "model")
	private String model;
	
	@Column(name = "flightNumber")
	private String flightNumber;
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flight_id")
    private List<Flight> flights;
	
	public Plane() {

	}

	public Plane(Long plane_id,  String model, String flightNumber) {
		this.plane_id = plane_id;
		this.model = model;
		this.flightNumber = flightNumber;
	}

	public Long getId() {
		return plane_id;
	}

	public void setId(Long plane_id) {
		this.plane_id = plane_id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	
	  @Override
	    public String toString() {
	        return "Plane{" +
	                "id=" + plane_id +
	                ", model=" + model +
	                ", flightNumber=" + flightNumber +
	               '}';
	    }
}