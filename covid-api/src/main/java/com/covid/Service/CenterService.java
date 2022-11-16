package com.covid.Service;

import com.covid.Exception.CenterException;
import com.covid.Exception.UserException;
import com.covid.Model.Center;

public interface CenterService {
	public Center addCenter(Center center,String key) throws CenterException, UserException;
	
}
