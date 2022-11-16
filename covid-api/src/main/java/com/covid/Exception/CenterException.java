package com.covid.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CenterException extends Exception{
	
	public CenterException(String msg){
		super(msg);
	}
		
	
}
