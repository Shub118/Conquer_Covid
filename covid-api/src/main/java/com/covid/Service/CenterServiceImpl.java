package com.covid.Service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.Exception.CenterException;
import com.covid.Exception.UserException;
import com.covid.Model.Appointment;
import com.covid.Model.Center;
import com.covid.Model.CurrentUserSession;
import com.covid.Model.User;
import com.covid.Model.VaccineInventory;
import com.covid.Repository.AppointmentRepo;
import com.covid.Repository.CenterRepo;
import com.covid.Repository.CurrentUserSessionRepo;
import com.covid.Repository.UserRepo;
import com.covid.Repository.VaccineInventoryRepo;

@Service
public class CenterServiceImpl implements CenterService{
	
	@Autowired
	AppointmentRepo appDao;
	
	@Autowired
	UserRepo uDao;
	
	@Autowired
	VaccineInventoryRepo viDao;
	
	@Autowired
	CenterRepo cDao;
	
	@Autowired
	CurrentUserSessionRepo cuDao;
	
	@Override
	public Center addCenter(Center center,String key) throws CenterException, UserException {
		
		CurrentUserSession cusr = cuDao.findByUuid(key);
		
		if(cusr == null) {
			throw new UserException("please LogIn First");
		}
		if(!cusr.getType().equals("ADMIN")) {
			throw new UserException("This feature is only for Admin");
		}
		
		VaccineInventory vi = viDao.findByState(center.getState());
		if(vi == null) {
			throw new CenterException("There is no Inventory in this state");
		}
		center.setVaccineInventory(vi);
		
		Center addedcenter =  cDao.save(center);
		
		return addedcenter;
	}

	@Override
	public String resolveAppointment(String mobileNo, String key) throws CenterException, UserException {
		CurrentUserSession cusr = cuDao.findByUuid(key);
		
		if(cusr == null) {
			throw new UserException("please LogIn First");
		}
		if(!cusr.getType().equals("ADMIN")) {
			throw new UserException("This feature is only for Admin");
		}
		
		User user = uDao.findByMobileNo(mobileNo);
		
		Appointment app = user.getAppointment();
		
		if(app == null) {
			throw new UserException("No appointment found");
		}
		VaccineInventory vi = viDao.findByState(user.getMember().getState());
		
		vi.setQuantity(vi.getQuantity()-1);
		
		viDao.save(vi);
		
		if(!user.getMember().isDsoe1_status()) {
			user.getMember().setDsoe1_status(true);
			user.getMember().setDose1_date(LocalDate.now());
		}
		else {
			user.getMember().setDsoe2_status(true);
			user.getMember().setDose2_date(LocalDate.now());
		}
		
		appDao.delete(app);
		
		uDao.save(user);
		return "Update succesfull";
	}

}
