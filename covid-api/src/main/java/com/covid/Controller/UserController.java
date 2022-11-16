package com.covid.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covid.Exception.UserException;
import com.covid.Model.CurrentUserSession;
import com.covid.Model.LoginDTO;
import com.covid.Model.User;
import com.covid.Service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserServiceImpl uService;
	
	
	@PostMapping("/register")
	public ResponseEntity<User> RegisterUser(@RequestBody User  user) throws UserException{
		
		User registered = uService.registerUser(user);
	
		return new ResponseEntity<>(registered,HttpStatus.ACCEPTED);
	
	}
	
	@PostMapping("/login")
	public ResponseEntity<CurrentUserSession> RegisterUser(@RequestBody LoginDTO  loginData) throws UserException{
		
		CurrentUserSession currentUser = uService.login(loginData);
	
		return new ResponseEntity<>(currentUser,HttpStatus.ACCEPTED);
	
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logOutUser(@RequestParam String key) throws UserException{
		String st = uService.logOut(key);
		return new ResponseEntity<>(st,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody User userNewData, @RequestParam String key) throws UserException {
		User updated = uService.updateUser(userNewData, key);
		return new ResponseEntity<>(updated,HttpStatus.ACCEPTED);

	}
}
