package com.covid.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.Exception.UserException;
import com.covid.Model.Center;
import com.covid.Model.CurrentUserSession;
import com.covid.Model.VaccineInventory;
import com.covid.Repository.CenterRepo;
import com.covid.Repository.CurrentUserSessionRepo;
import com.covid.Repository.VaccineInventoryRepo;

@Service
public class VaccineInventoryServImpl implements VaccineInventoryService{

	@Autowired
	VaccineInventoryRepo viDao;
	
	@Autowired
	CenterRepo cDao;
	
	@Autowired
	CurrentUserSessionRepo cuDao;
	
	@Override
	public VaccineInventory addVaccineInventory(VaccineInventory vi, String key) throws UserException {
		CurrentUserSession cusr = cuDao.findByUuid(key);
		
		if(cusr == null) {
			throw new UserException("please LogIn First");
		}
		if(!cusr.getType().equals("ADMIN")) {
			throw new UserException("This feature is only for Admin");
		}
		
		if(!vi.getCenters().isEmpty()) {
			for(Center c: vi.getCenters()) {
				c.setVaccineInventory(vi);
			}
		}
		
		return viDao.save(vi);
		
	}

}
