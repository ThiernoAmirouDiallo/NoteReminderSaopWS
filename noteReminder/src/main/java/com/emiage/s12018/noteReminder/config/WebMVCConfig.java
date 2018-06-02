package com.emiage.s12018.noteReminder.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;




@SuppressWarnings("deprecation")
@Configuration
public class WebMVCConfig extends WebMvcConfigurerAdapter {
@Override
public void addViewControllers(ViewControllerRegistry registry) {
	
	registry.addViewController("/login").setViewName("login");
	//registry.addViewController("/logout").setViewName("logout");
	
}

//@Override
//public void addViewControllers(ViewControllerRegistry registry) {
//	super.addViewControllers(registry);
//    registry.addViewController("/login").setViewName("auth/login");
//    registry.setOrder(Ordered.HIGHEST_PRECEDENCE);		
//}




}
