package com.covid.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covid.Model.Center;

public interface CenterRepo extends JpaRepository<Center,Integer> {
	
	
	public List<Center> findByPinCode(String pincode);
	public List<Center> findByCity(String city);
	
}
