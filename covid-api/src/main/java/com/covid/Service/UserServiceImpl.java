package com.covid.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.Exception.CenterException;
import com.covid.Exception.UserException;
import com.covid.Exception.VaccineInventoryException;
import com.covid.Model.Appointment;
import com.covid.Model.Center;
import com.covid.Model.CurrentUserSession;
import com.covid.Model.LoginDTO;
import com.covid.Model.SLOT;
import com.covid.Model.User;
import com.covid.Model.VaccineInventory;
import com.covid.Repository.AppointmentRepo;
import com.covid.Repository.CenterRepo;
import com.covid.Repository.CurrentUserSessionRepo;
import com.covid.Repository.UserRepo;
import com.covid.Repository.VaccineInventoryRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo usDao;
	
	@Autowired
	CurrentUserSessionRepo cuDao;
	
	@Autowired
	VaccineInventoryRepo viDao;
	
	@Autowired
	CenterRepo cDao;
	
	@Autowired
	AppointmentRepo appDao;
	
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
		
			userNewData.setMember(us.getMember());
		
		userNewData.setId(us.getId());
		
		return usDao.save(userNewData);
	}



	@Override
	public Appointment getAppointment(String key) throws UserException, CenterException, VaccineInventoryException {
		Appointment app = new Appointment();
		
		
		
		CurrentUserSession currentUser = cuDao.findByUuid(key);
		if(currentUser == null) {
			throw new UserException("No current user with this key");
		}
		
		Optional<User> userData = usDao.findById(currentUser.getUserId());
		if(userData.isEmpty()) {
			throw new UserException("No user Data Found");
		}
		User us = userData.get();
		
		if (us.getMember().isDsoe1_status() && us.getMember().isDsoe2_status()) {
			throw new UserException("Can't book appointment for you both dose are completed");
		}
		
		VaccineInventory vi = viDao.findByState(us.getMember().getState());
		
		if(vi == null) {
			throw new CenterException("No center near you found");
		}
		
		if(vi.getQuantity()<1) {
			throw new VaccineInventoryException("Dose Not available at this time try later");
		}
		
		List<Center> centerByPin = cDao.findByPinCode(us.getMember().getPinCode());
		
		if(centerByPin.isEmpty()) {
			List<Center> centerByCity = cDao.findByCity(us.getMember().getCity());
			if(centerByCity.isEmpty()) {
				throw new CenterException("No center near you found");
			}
			centerByCity.get(0).getAppointment().add(app);
			app.setCenter(centerByCity.get(0));
			if(centerByCity.get(0).getAppointment().size()<10) {
				int x = centerByCity.get(0).getAppointment().size();
				SLOT s = SLOT.SLOT1;
				if(x==1) s = SLOT.SLOT2;
				if(x==2) s = SLOT.SLOT3;
				if(x==3) s = SLOT.SLOT4;
				if(x==4) s = SLOT.SLOT5;
				if(x==5) s = SLOT.SLOT6;
				if(x==6) s = SLOT.SLOT7;
				if(x==7) s = SLOT.SLOT8;
				if(x==8) s = SLOT.SLOT9;
				if(x==9) s = SLOT.SLOT10;
				app.setSlot(s);
			}else {
				throw new CenterException("All slots are booked");
			}
		}else {
			centerByPin.get(0).getAppointment().add(app);
			app.setCenter(centerByPin.get(0));
			if(centerByPin.get(0).getAppointment().size()<10) {
				int x = centerByPin.get(0).getAppointment().size();
				SLOT s = SLOT.SLOT1;
				if(x==1) s = SLOT.SLOT2;
				if(x==2) s = SLOT.SLOT3;
				if(x==3) s = SLOT.SLOT4;
				if(x==4) s = SLOT.SLOT5;
				if(x==5) s = SLOT.SLOT6;
				if(x==6) s = SLOT.SLOT7;
				if(x==7) s = SLOT.SLOT8;
				if(x==8) s = SLOT.SLOT9;
				if(x==9) s = SLOT.SLOT10;
				app.setSlot(s);
			}else {
				throw new CenterException("All slots are booked");
			}
		}
		
		vi.setQuantity(vi.getQuantity()-1);
		viDao.save(vi);
		app.setUser(us);
		app.setBookingStatus(true);
		app.setDateOfBooking(LocalDate.now());
		
		return appDao.save(app);
	}
	
}
