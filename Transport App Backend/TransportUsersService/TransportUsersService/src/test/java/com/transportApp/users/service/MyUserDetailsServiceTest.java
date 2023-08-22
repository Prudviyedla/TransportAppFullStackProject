package com.transportApp.users.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import com.transportApp.users.model.Users;
import com.transportApp.users.repository.UserRepo;



@ExtendWith(MockitoExtension.class)
class MyUserDetailsServiceTest {

	// @Mock annotation is used to create the mock object to be injected
	@Mock
	private UserRepo userRepo;

	// @InjectMocks annotation is used to create and inject the mock object
	@InjectMocks
	private MyUserDetailsService service;
	
	private UserDetails ud;
	Users user;
	
	@Test
	public void givenUsernameShouldReturnUsernameAndPassword() {
		user = new Users(1, "Naveed", "Shaik", "Naveed321", "12345678", "Vijayawada", "Naved3@gmail.com", "6548979043",
				"12345678");
		when(userRepo.findByUserName(user.getUserName())).thenReturn(user);
		ud = service.loadUserByUsername(user.getUserName());
		verify(userRepo,times(1)).findByUserName(user.getUserName());
		
	}	
}
