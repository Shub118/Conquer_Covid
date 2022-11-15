package com.covid.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covid.Model.User;

public interface UserRepo  extends JpaRepository<User, Integer>{

}
