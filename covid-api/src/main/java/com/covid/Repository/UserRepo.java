package com.covid.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covid.Model.User;

@Repository
public interface UserRepo  extends JpaRepository<User, Integer>{
	public User findByMobileNo(String mobl);

}
