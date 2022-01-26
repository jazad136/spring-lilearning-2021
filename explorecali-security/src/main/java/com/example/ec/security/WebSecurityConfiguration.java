package com.example.ec.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/** 
 * Configuration class for application web security, login, and blockings.
 * 
 * @author Mary Ellen Bowman, with syntactic sugar by Jonathan A. Saddler
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Entry points
//        http.authorizeRequests()
//                .antMatchers("/packages/**").permitAll()
//                .antMatchers("/tours/**").permitAll()
//                .antMatchers("/ratings/**").permitAll()
//                .antMatchers("/users/signin").permitAll()
//                .antMatchers("/v3/api-docs/**","/swagger-ui/**", "/swagger-ui.html").permitAll()
//                // Disallow everything else..
//                .anyRequest().authenticated();
    	// we should be using mvcMatchers instead of antMatchers here. 
    	
    	http.authorizeRequests()
	    	.mvcMatchers("/packages/**").permitAll()
	    	.mvcMatchers("/tours/**").permitAll()
	    	.mvcMatchers("/ratings/**").permitAll()
	    	.mvcMatchers("/users/signin").permitAll()
	    	.mvcMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll();
        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}
