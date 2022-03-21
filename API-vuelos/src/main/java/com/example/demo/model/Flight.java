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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "flight")
@EntityListeners(AuditingEntityListener.class)
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "flight_id")
	private Long flight_id;

	
	@Column(name = "planeName")
	private String planeName;
	
	@Column(name = "passengerName")
	private String passengerName;
	
	@Column(name = "destination")
	private String destination;
	
	
	
	@OneToOne 
	//@OneToOne(fetch = FetchType.LAZY, mappedBy = "plane_id")
    private Plane plane;
	
	@OneToMany ( fetch = FetchType.LAZY, mappedBy = "passenger_id")
	 private List<Passenger> passenger;
	
	public Flight() {

	}

	
	public Long getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(Long flight_id) {
		this.flight_id = flight_id;
	}

	public String getPlaneName() {
		return planeName;
	}

	public void setPlaneName(String planeName) {
		this.planeName = planeName;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public List<Passenger> getPassenger() {
		return passenger;
	}

	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}
	public Flight(Long flight_id,  String planeName, String passengerName, String destination) {
		this.flight_id = flight_id;
		this.planeName = planeName;
		this.passengerName = passengerName;
		this.destination = destination;
	}

	@Override
    public String toString() {
        return "Flight{" +
                "id=" + flight_id +
                ", planeName=" + planeName +
                ", passengerName=" + passengerName +
                 ",destination=" + destination +
               '}';
    }
}