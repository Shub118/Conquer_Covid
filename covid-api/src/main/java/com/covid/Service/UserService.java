package com.covid.Service;

import com.covid.Exception.CenterException;
import com.covid.Exception.UserException;
import com.covid.Exception.VaccineInventoryException;
import com.covid.Model.Appointment;
import com.covid.Model.CurrentUserSession;
import com.covid.Model.LoginDTO;
import com.covid.Model.User;

public interface UserService {
	public User registerUser(User user) throws UserException;
	
	public CurrentUserSession login(LoginDTO ld) throws UserException;

	public String logOut(String key) throws UserException;
	
	public User updateUser(User user,String key) throws UserException;
	
	public Appointment getAppointment(String key) throws UserException, CenterException, VaccineInventoryException;
}
