package com.covid.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covid.Model.Center;

public interface CenterRepo extends JpaRepository<Center,Integer> {
	
	
	
}
