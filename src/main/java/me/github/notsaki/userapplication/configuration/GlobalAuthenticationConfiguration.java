//package me.github.notsaki.userapplication.configuration;
//
//import org.apache.commons.logging.Log;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
//import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
//
//import java.util.Map;
//
//@Configuration
//public class GlobalAuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
//	private final ApplicationContext context;
//
//	public GlobalAuthenticationConfiguration(ApplicationContext context) {
//		this.context = context;
//	}
//
//	@Override
//	public void init(AuthenticationManagerBuilder auth) {
//		Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(EnableGlobalAuthentication.class);
//	}
//}
