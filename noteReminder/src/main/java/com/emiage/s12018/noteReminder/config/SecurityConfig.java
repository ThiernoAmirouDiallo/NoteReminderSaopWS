package com.emiage.s12018.noteReminder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
@EnableGlobalMethodSecurity( securedEnabled = true )
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	

	//autrorisation des urls non authentifiées par spring security
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			//la securité du web service est delegué a soap ws configuré dans la classe WebServiceConfig.java
			.antMatchers("/ws/**").permitAll()
			//accès libre a la console de la base de données H2
			.antMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated()
			;
		

        http.csrf().disable();
        http.headers().frameOptions().disable();    
	}

	//bean de cryptage du mot de passe de type bcryp (salt generé automatiquement)
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	//service de recuperation des info utilisateur
	@Autowired
	private UserDetailsService userService;
	
	//parametrage de l'authentification spring securité sur la quelle va resposer l'authentification soap ws security
	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
}