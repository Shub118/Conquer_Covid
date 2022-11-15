package com.covid.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covid.Model.CurrentUserSession;

public interface CurrentUseSessionRepo  extends JpaRepository<CurrentUserSession, Integer>{

}
