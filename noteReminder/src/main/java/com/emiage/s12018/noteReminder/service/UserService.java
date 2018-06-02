package com.emiage.s12018.noteReminder.service;

import com.emiage.s12018.noteReminder.entity.Users;

public interface UserService {
	public Users findByUsername(String username);
}
