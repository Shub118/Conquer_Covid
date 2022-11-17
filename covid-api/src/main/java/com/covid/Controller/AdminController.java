package com.covid.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covid.Exception.CenterException;
import com.covid.Exception.UserException;
import com.covid.Model.Center;
import com.covid.Model.CurrentUserSession;
import com.covid.Model.LoginDTO;
import com.covid.Model.User;
import com.covid.Model.VaccineInventory;
import com.covid.Service.AdminService;
import com.covid.Service.CenterService;
import com.covid.Service.UserServiceImpl;
import com.covid.Service.VaccineInventoryServImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService aService;
	
	@Autowired
	CenterService cService;
	
	@Autowired
	UserServiceImpl uService;
	
	@Autowired
	VaccineInventoryServImpl viService;
	
	
	@PostMapping("/register")
	public ResponseEntity<User> RegisterUser(@RequestBody User  user, @RequestParam String key) throws UserException{
		
		User registered = aService.addAdmin(user, key) ;
	
		return new ResponseEntity<>(registered,HttpStatus.ACCEPTED);
	
	}
	
	@PostMapping("/login")
	public ResponseEntity<CurrentUserSession> login(@RequestBody LoginDTO  loginData) throws UserException{
		
		CurrentUserSession currentUser = uService.login(loginData);
	
		return new ResponseEntity<>(currentUser,HttpStatus.ACCEPTED);
	
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logOut(@RequestParam String key) throws UserException{
		String st = uService.logOut(key);
		return new ResponseEntity<>(st,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/addvaccineinventory")
	public ResponseEntity<VaccineInventory> addVaccineInventory(@RequestBody VaccineInventory vi,@RequestParam String key) throws  UserException{
		VaccineInventory c = viService.addVaccineInventory(vi, key);
		return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/addcenter")
	public ResponseEntity<Center> addCenter(@RequestBody Center center,@RequestParam String key) throws CenterException, UserException{
		Center c = cService.addCenter(center, key);
		return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
	}
	
	
	
}
