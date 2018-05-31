package com.emiage.s12018.noteReminder.dao;

import com.emiage.s12018.noteReminder.entity.Users;

public interface UserService {

		public Users findByEmail(String email);
		
	}

