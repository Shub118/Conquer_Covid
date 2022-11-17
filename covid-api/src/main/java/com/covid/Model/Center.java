package com.covid.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	private String pinCode;
	private String city;
	private String state;
	
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	VaccineInventory vaccineInventory;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "center")
//	@JoinColumn(name = "appointment_id")
	List<Appointment> appointment = new ArrayList<>();
}
