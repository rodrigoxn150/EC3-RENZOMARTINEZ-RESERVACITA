package com.idat.EC3RENZOMARTINEZRESERVARCITA.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailService service;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     	auth.inMemoryAuthentication().withUser("renzo1").password(encriptado().encode("12345") ).roles("USER");
		auth.inMemoryAuthentication().withUser("renzo2").password(encriptado().encode("123456")).roles("USER");
		auth.inMemoryAuthentication().withUser("renzo3").password(encriptado().encode("1234567")).roles("USER");
		auth.userDetailsService(service).passwordEncoder(encriptado());
		}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/reservacita/**").access("hasRole('USER')")
			.and()
			.httpBasic()
		    .and()
			.csrf().disable();
		
		
	}
	
	@Bean
	public JwtTokenStore store() {
		
		return new JwtTokenStore(accessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("te-sigo-en-youtube");
		return tokenConverter;
	}

	
	
	@Bean
	public PasswordEncoder encriptado() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}


}
