package com.covid.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@EqualsAndHashCode
@Data
@Entity
public class Center {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cid;
	
	private String centerName;
	private String Pincode;
	private String city;
	private String state;
	
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	VaccineInventory vaccineInventory;
	
}
