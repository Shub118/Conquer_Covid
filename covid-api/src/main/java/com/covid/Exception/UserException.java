package com.covid.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserException extends Exception{
	
	public UserException(String msg) {
		super(msg);
	}
}
