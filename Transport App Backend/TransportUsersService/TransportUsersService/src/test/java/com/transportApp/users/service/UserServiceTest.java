package com.transportApp.users.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.transportApp.users.model.Users;
import com.transportApp.users.repository.UserRepo;



@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	//@Mock annotation is used to create the mock object to be injected
	@Mock
	private UserRepo userRepo;

	//@InjectMocks annotation is used to create and inject the mock object
	@InjectMocks
	private UserServiceImpl userservice;

	private Users user;
	private List<Users> usersList;

	@BeforeEach
	public void setUp() {
		user = new Users(1, "Naveed", "Shaik", "Naveed321", "12345678", "Vijayawada", "Naved3@gmail.com", "6548979043",
				"12345678");
	}

	@AfterEach
	public void tearDown() {
		user = null;
		usersList = null;

	}

	@Test
	public void givenUserToSaveShouldReturnUser() {
		when(userRepo.save(any())).thenReturn(user);
		userservice.saveUser(user);
		verify(userRepo, times(1)).save(any());
	}

	@Test
	public void givenUserToSaveShouldReturnNull() {
		when(userRepo.save(any())).thenReturn(null);
		userservice.saveUser(user);
		verify(userRepo, times(1)).save(any());
	}

	@Test
	public void givenGetAllUsersThenShouldReturnListOfAllUsers() {
		userRepo.save(user);
		when(userRepo.findAll()).thenReturn(usersList);
		usersList = (List<Users>) userRepo.findAll();
		verify(userRepo, times(1)).save(user);
		verify(userRepo, times(1)).findAll();
	}

	@Test
	public void givenIdshouldReturnUserOfThatId() {
		when(userRepo.findById(user.getId())).thenReturn(Optional.ofNullable(user));
		assertThat(userservice.findById(user.getId())).isEqualTo(user);
	}
	
	@Test
	public void givenIdToDeleteThenShouldDeleteTheUser() {
		userRepo.findById(user.getId());
		userRepo.delete(user);
//		assertThat(userservice.deleteUsersById(user.getId())).isEqualTo(user);
		verify(userRepo,times(1)).delete(user);
	}
	@Test
	public void givenUserNameThenshouldReturnUser() {
		when(userRepo.findByUserName(user.getUserName())).thenReturn(user);
		assertThat(userservice.findByUserName(user.getUserName())).isEqualTo(1);
	}
	
	@Test
	public void givenUserShouldReturnUpdatedUser() {
		when(userRepo.save(any())).thenReturn(user);
		userservice.updateUser(user);
		verify(userRepo, times(1)).save(any());
		
	}
}
