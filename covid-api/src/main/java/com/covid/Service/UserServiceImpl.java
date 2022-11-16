package com.covid.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.Exception.UserException;
import com.covid.Model.CurrentUserSession;
import com.covid.Model.LoginDTO;
import com.covid.Model.User;
import com.covid.Repository.CurrentUserSessionRepo;
import com.covid.Repository.UserRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo usDao;
	
	@Autowired
	CurrentUserSessionRepo cuDao;
	
	@Override
	public User registerUser(User user) throws UserException {
		user.setRole("USER");
		
		User existingUser = usDao.findByMobileNo(user.getMobileNo());
		
		if(existingUser != null) {
			throw new UserException("User with this mobile no. already exists");
		}
		
		User registeredUser = usDao.save(user);
		
		return registeredUser;
	}



	@Override
	public CurrentUserSession login(LoginDTO ld) throws UserException {
		
		User user = usDao.findByMobileNo(ld.getMobileNo());
		if(user == null) {
			throw new UserException("Please Enter Correct Mobile Number");
		}
		
		Optional<CurrentUserSession> alreadyLoggedIn =  cuDao.findById(user.getId());
		if(alreadyLoggedIn.isPresent()) {
			throw new UserException("User is Already logged In");
		}
		if(!user.getPassword().equals(ld.getPassword())) {
			throw new UserException("please Enter Correct Password");
		}
		CurrentUserSession sessionInfo = cuDao.save( new CurrentUserSession(user.getId(),RandomString.make(6),LocalDateTime.now(),user.getRole()));
		
		return sessionInfo;
	}
	
	@Override
	public String logOut(String key) throws UserException {
		CurrentUserSession user = cuDao.findByUuid(key);
		if(user == null) {
			throw new UserException("No user found by this Key");
		}
		cuDao.delete(user);
		
		return "Logged Out";
	}



	@Override
	public User updateUser(User userNewData,String key) throws UserException {
		CurrentUserSession currentUser = cuDao.findByUuid(key);
		if(currentUser == null) {
			throw new UserException("No current user with this key");
		}
		
		Optional<User> userData = usDao.findById(currentUser.getUserId());
		
		if(userData.isEmpty()) {
			throw new UserException("No user Data Found");
		}
		User us = userData.get();
		if(userNewData.getMember() == null) {
			userNewData.setMember(us.getMember());
		}
		userNewData.setId(us.getId());
		
		return usDao.save(userNewData);
	}
	
}
