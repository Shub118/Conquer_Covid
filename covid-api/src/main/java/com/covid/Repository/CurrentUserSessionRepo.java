package com.covid.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covid.Model.CurrentUserSession;

@Repository
public interface CurrentUserSessionRepo  extends JpaRepository<CurrentUserSession, Integer>{

	public CurrentUserSession findByUuid(String uuid);
	
	

}
