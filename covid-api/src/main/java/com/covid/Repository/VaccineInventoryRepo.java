package com.covid.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covid.Model.VaccineInventory;

public interface VaccineInventoryRepo extends JpaRepository<VaccineInventory, Integer> {
	
	public VaccineInventory findByState(String state);
	
}
