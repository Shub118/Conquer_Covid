package com.covid.Service;

import com.covid.Exception.UserException;
import com.covid.Model.User;

public interface AdminService {
	
	public User addAdmin(User user,String key) throws UserException;
	
}
