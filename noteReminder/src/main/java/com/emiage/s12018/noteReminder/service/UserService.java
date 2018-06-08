package com.emiage.s12018.noteReminder.service;

import com.emiage.s12018.noteReminder.entity.Users;

//implementé par UserServiceImp qui passe par le user repository pour recuperer les informations des utilisateurs
public interface UserService {
	public Users findByUsername(String username);
}
