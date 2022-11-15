package com.covid.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covid.Model.Member;

public interface MemberRepo extends JpaRepository<Member, Integer>{

}
