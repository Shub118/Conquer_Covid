package com.covid.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covid.Exception.CenterException;
import com.covid.Exception.UserException;
import com.covid.Exception.VaccineInventoryException;
import com.covid.Model.VaccineInventory;
import com.covid.Service.CenterServiceImpl;
import com.covid.Service.VaccineInventoryServImpl;

@RestController
@RequestMapping("/center")
public class CenterController {
	
	@Autowired
	CenterServiceImpl cService;
	
	@Autowired
	VaccineInventoryServImpl viService;
	
	@PutMapping("/updateInventory")
	public ResponseEntity<VaccineInventory> updateInventory(@RequestBody VaccineInventory vi, @RequestParam String key) throws UserException, VaccineInventoryException {
		VaccineInventory updated = viService.updateInventory(vi, key);
		return new ResponseEntity<>(updated,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updatedose")
	public ResponseEntity<String> updateUser(@RequestParam String mobileNo, @RequestParam String key) throws CenterException, UserException{
		String st = cService.resolveAppointment(mobileNo, key);
		
		return new ResponseEntity<>(st,HttpStatus.OK);
	}
}
