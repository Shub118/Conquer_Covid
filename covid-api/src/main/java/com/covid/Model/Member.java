package com.covid.Model;

import java.time.LocalDate;
import java.util.List;

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

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data

public class Member {
	
	boolean dsoe1_status;
	LocalDate dose1_date;
	
	boolean dsoe2_status;
	LocalDate dose2_date;

	String name;
	
	String Gender;
	
	LocalDate Dob;
	
	String city;
	
	String state;
	
	String pinCode;
	
	String panNo;
	
	String adharNo;
	
	
}
