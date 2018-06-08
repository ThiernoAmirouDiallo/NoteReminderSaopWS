package com.emiage.s12018.noteReminder.entity;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//classe users liée a la tables users
@Entity
@Table( name="users" )
public class Users implements Serializable {
	@Id
	@SequenceGenerator(name="USERS_GENERATOR", sequenceName="USERS_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERS_GENERATOR")
	private Long idUser;
	
	private String  username;
	private String password;
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String usermane) {
		this.username = usermane;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Users() {
		super();
	}
	@Override
	public String toString() {
		return "Users [idUser=" + idUser + ", username=" + username + ", password=" + password 
				+ "]";
	}
	
}