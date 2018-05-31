package com.emiage.s12018.noteReminder.entity;
import java.io.Serializable;


import java.util.Collection;

import java.util.HashSet;
import java.util.Set;

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

@Entity
@Table( name="users" )
public class Users implements Serializable {
	@Id
	@SequenceGenerator(name="USERS_GENERATOR", sequenceName="USERS_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERS_GENERATOR")
	private Long idUser;
	
	private String  username;
	private String password;
	private String droit;
	private String matricule;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="USERS_ROLES")
	private Set<Role> roles = new HashSet<Role>();
	
	private boolean actived;
	
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public boolean getActived() {
		return actived;
	}
	public void setActived(boolean actived) {
		this.actived = actived;
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

	
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Users() {
		super();
	}
	public String getDroit() {
		return droit;
	}
	public void setDroit(String droit) {
		this.droit = droit;
	}
	
	
	
	
}