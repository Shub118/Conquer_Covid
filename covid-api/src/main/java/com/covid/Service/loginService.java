package com.covid.Service;

import com.covid.Model.CurrentUserSession;
import com.covid.Model.LoginDTO;

public interface loginService {
	public CurrentUserSession login(LoginDTO ldt);
	public String logOut();
}
