//package me.github.notsaki.userapplication.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//public class AuthenticationManagerConfiguration {
//	private final UserDetailsService userDetailsService;
//	private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//	public AuthenticationManagerConfiguration(
//			UserDetailsService userDetailsService,
//			BCryptPasswordEncoder bCryptPasswordEncoder
//	) {
//		this.userDetailsService = userDetailsService;
//		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//	}
//
//	@Bean
//	protected AuthenticationManager authenticationManagerBean(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(this.userDetailsService)
//				.passwordEncoder(this.bCryptPasswordEncoder);
//
//		return auth.build();
//	}
//}
