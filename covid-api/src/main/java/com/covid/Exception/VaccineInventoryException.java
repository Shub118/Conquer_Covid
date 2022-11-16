package com.covid.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VaccineInventoryException extends Exception {
	public VaccineInventoryException(String msg){
		super(msg);
	}
}
