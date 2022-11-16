package com.covid.Service;

import com.covid.Exception.UserException;
import com.covid.Model.VaccineInventory;

public interface VaccineInventoryService {
	public VaccineInventory addVaccineInventory(VaccineInventory vi,String key) throws UserException;
}
