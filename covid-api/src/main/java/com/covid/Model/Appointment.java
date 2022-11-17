package com.covid.Model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer aid;
	
	private LocalDate dateOfBooking;
	
	private boolean bookingStatus;
	
	private SLOT slot;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	User user;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	Center center;
}
