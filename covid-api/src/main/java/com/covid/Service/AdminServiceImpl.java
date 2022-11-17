package com.covid.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.Exception.UserException;
import com.covid.Model.CurrentUserSession;
import com.covid.Model.User;
import com.covid.Repository.CurrentUserSessionRepo;
import com.covid.Repository.UserRepo;


@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	UserRepo uDao;
	
	@Autowired
	CurrentUserSessionRepo cuDao;
	
	@Override
	public User addAdmin(User user,String key) throws UserException {
		CurrentUserSession curr = cuDao.findByUuid(key);
		
		if(curr == null) {
			throw new UserException("Please login first");
		}
		if(curr.getType() != "ADMIN") {
			throw new UserException("Please login with admin id");
		}
		user.setRole("ADMIN");
		
		return uDao.save(user);
	}



}
