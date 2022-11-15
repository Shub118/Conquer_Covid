package com.covid.Model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer memberId;
	
	boolean dsoe1_status;
	LocalDate dose1_date;
	
	boolean dsoe2_status;
	LocalDate dose2_date;

	String name;
	String city;
	String state;
	String pinCode;
	String panNo;
	String adharNo;
	
}
