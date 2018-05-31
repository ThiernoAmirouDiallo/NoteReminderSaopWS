package com.emiage.s12018.noteReminder.dao;

import org.springframework.data.jpa.repository.JpaRepository;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emiage.s12018.noteReminder.entity.Role;




public interface RoleRepository extends JpaRepository<Role,String>{
	
	

}
