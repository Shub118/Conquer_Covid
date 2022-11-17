package com.covid.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covid.Model.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer>{
	
}
