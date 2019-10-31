package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityCon extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("user")
			.password("{noop}pass") // Spring Security 5 requires specifying the password storage format
			.roles("USER");
	}
//    @Override  
//    protected void configure(HttpSecurity http) throws Exception {  
//        http  
//        .authorizeRequests()
//	        .antMatchers("/home").permitAll()
//	        .anyRequest().authenticated()
//	        
//	        .and()
////	        .httpBasic();
//	    .formLogin()
//	        .loginPage("/")
////	        .defaultSuccessUrl("/home")
//	        .permitAll()	
////            .failureUrl("/loginerror")  
////            .permitAll()
//	        .and()
//	        
//	    .logout()
//	        .permitAll();

////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
////        authenticationMgr.inMemoryAuthentication().withUser("lpz").password("lpzcode");
////    }

}
