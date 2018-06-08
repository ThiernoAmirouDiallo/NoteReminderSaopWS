package com.emiage.s12018.noteReminder.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.emiage.s12018.noteReminder.entity.Users;

//utile pour dire a spring securite quels sont les detail d'un utilisateur et ses status
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 3185970362329652822L;
	
	private Users user;
	
	public UserDetailsImpl(Users user){
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		
		//pas de roles pour ce projet
			//List<Role> roles = user.getRoles();
			//for( Role role : roles ) {
			//	authorities.add( new SimpleGrantedAuthority(role.getRole()) ); 
			//}
			return authorities;		
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
