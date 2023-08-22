package com.transportApp.users.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.transportApp.users.filter.JWTFilter;
import com.transportApp.users.service.MyUserDetailsService;

import io.jsonwebtoken.lang.Arrays;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer  {
		
		@Autowired
		MyUserDetailsService userService;
		
		@Autowired
		JWTFilter filter;
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userService);
		}
		
		@Bean
		public PasswordEncoder getPasswordEncoder() {
			
			return NoOpPasswordEncoder.getInstance();
		}
		
		@Bean(name=BeanIds.AUTHENTICATION_MANAGER)
		@Override
		protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
		}
		
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//				http.csrf().disable().authorizeRequests().antMatchers("/login")
//				.permitAll().anyRequest().authenticated()
//				.and().exceptionHandling().and().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//				
//				http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
//		}
//		
//		  @Override public void configure(WebSecurity web) throws Exception {
//		  web.ignoring().antMatchers("/register"); 
//		  }
		@Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable();
	    }

//	    @Override
//	    public void addCorsMappings(CorsRegistry registry) {
//	        registry.addMapping("/**").allowedMethods("*");
//	    }
		 
		
			
}

