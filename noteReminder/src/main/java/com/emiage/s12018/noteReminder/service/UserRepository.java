package com.emiage.s12018.noteReminder.service;

import java.util.Optional;









import org.springframework.data.jpa.repository.JpaRepository;

import com.emiage.s12018.noteReminder.entity.Users;










public interface UserRepository extends JpaRepository<Users,String> {

	


	Users findByUsername(String username);

	

	
	
}