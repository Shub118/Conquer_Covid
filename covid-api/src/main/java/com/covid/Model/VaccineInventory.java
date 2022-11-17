package com.covid.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class VaccineInventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer inventoryId;
	
	private String vaccineName;
	private Integer quantity;
	private String state;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccineInventory")
//	@JoinColumn(name = "center_id")
	List<Center> centers = new ArrayList<>();
}
