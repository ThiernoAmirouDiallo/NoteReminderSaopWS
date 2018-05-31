package com.emiage.s12018.noteReminder.entity;

import java.io.Serializable;
import java.util.HashSet;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
public class Role implements Serializable {
	@Id
	@SequenceGenerator(name="ROLE_GENERATOR", sequenceName="ROLE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLE_GENERATOR")
	private Long idRole;
	
	public Long getIdRole() {
		return idRole;
	}
	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}
	private String role;
	private String description;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Role(String role, String description) {
		super();
		this.role = role;
		this.description = description;
	}
	public Role() {
		super();
	}
	
	
}