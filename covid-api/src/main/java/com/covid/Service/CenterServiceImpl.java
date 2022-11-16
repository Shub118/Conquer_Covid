package com.covid.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.Exception.CenterException;
import com.covid.Exception.UserException;
import com.covid.Model.Center;
import com.covid.Model.CurrentUserSession;
import com.covid.Model.VaccineInventory;
import com.covid.Repository.CenterRepo;
import com.covid.Repository.CurrentUserSessionRepo;
import com.covid.Repository.VaccineInventoryRepo;

@Service
public class CenterServiceImpl implements CenterService{
	
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

}
