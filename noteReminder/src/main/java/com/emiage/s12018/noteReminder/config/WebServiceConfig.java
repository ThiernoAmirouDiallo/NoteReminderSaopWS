package com.emiage.s12018.noteReminder.config;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.soap.security.xwss.callback.SpringDigestPasswordValidationCallbackHandler;
import org.springframework.ws.soap.security.xwss.callback.SpringPlainTextPasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//Enable Spring Web Services
@EnableWs
// Spring Configuration
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter{
	// MessageDispatcherServlet
	// ApplicationContext
	// url -> /ws/*

	private static final Logger log = LoggerFactory.getLogger(WebServiceConfig.class);

	//configuration du servlet en charge des requetes venant sur le web service
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
	}

	//parametrage du webservice, name space, endpoint, url WSDL
	// /ws/notes.wsdl
	// course-details.xsd
	@Bean(name = "notes")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema notesSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("NotePort");
		definition.setTargetNamespace("http://emiage2018s1.com/notes");
		definition.setLocationUri("/ws");
		definition.setSchema(notesSchema);
		return definition;
	}

	//on donne la sture des objets: utile pour la generation du WSDL
	@Bean
	public XsdSchema notesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("notes-details.xsd"));
	}
	
 
	//XwsSecurityInterceptor interception pour authentification des requetes ou rejets
	@Bean
	public XwsSecurityInterceptor securityInterceptor(){
		XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
		//Callback Handler -> SimplePasswordValidationCallbackHandler
		
		//securityInterceptor.setCallbackHandler(callbackHandler());
		securityInterceptor.setCallbackHandler(springPlainTextPasswordValidationCallbackHandler());
		//securityInterceptor.setCallbackHandler(digestSpringCallbackHandler());
		
		//parametre de securite : type de mot de passe
		//Security Policy -> securityPolicy.xml
		securityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
		return securityInterceptor;
	}
	
	//dans le cas de l'utiliation d'un user et mot de passe simple: in merory dans le dictionnaire
	//(non utilisé dans ce projet)
	@Bean
	public SimplePasswordValidationCallbackHandler callbackHandler() {
		SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
		handler.setUsersMap(Collections.singletonMap("user", "password"));
		return handler;
	}
	
	//dans le cas de l'utiliation d'un digest user token (non utilisé dans ce projet)
	@Bean
	public SpringDigestPasswordValidationCallbackHandler digestSpringCallbackHandler() {
		SpringDigestPasswordValidationCallbackHandler handler = new SpringDigestPasswordValidationCallbackHandler();
		handler.setUserDetailsService(userService);
		return handler;
	}
	
	
	
	// @Bean
    public SpringPlainTextPasswordValidationCallbackHandler springPlainTextPasswordValidationCallbackHandler() {
        SpringPlainTextPasswordValidationCallbackHandler callbackHandler = new SpringPlainTextPasswordValidationCallbackHandler();
        try { 
            callbackHandler.setAuthenticationManager(authenticationManager);
        } catch(Exception e) {
            log.error(e.getMessage());
        }
        return callbackHandler;
    }
	
    //on inject le builder qui va servire a construire l'authentification manage a donné a l'intercepteur de la securité 
    //pour authentifier les requetes
    @Autowired
    private AuthenticationManagerBuilder builder;
    
    //utile si on veut passer par une authentification digest password
    @Autowired
	private UserDetailsService userService;

	//@Autowired
    //private AuthenticationManager authenticationManager;
    private AuthenticationManager authenticationManager = new AuthenticationManager() {           
        @Override
        public Authentication authenticate(Authentication authentication)
                throws AuthenticationException {
            return builder.getOrBuild().authenticate(authentication);
        }
    };
    

    //on ajoute l'intercepteur configuré
	//Interceptors.add -> XwsSecurityInterceptor
	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(securityInterceptor());
	}
}
