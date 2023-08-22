package com.transportApp.users.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transportApp.users.model.Users;
import com.transportApp.users.service.UserService;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Mock
	private UserService userService;
	@InjectMocks
	private UserController usrController;

	Users user;
	List<Users> usrList;

	@BeforeEach
	public void setUp() {
		user = new Users(1, "Naveed", "Shaik", "Naveed321", "12345678", "Vijayawada", "Naved3@gmail.com", "6548979043",
				"12345678");
		mockMvc = MockMvcBuilders.standaloneSetup(usrController).build();
	}

	@AfterEach
	public void tearDown() {
		user = null;
		usrList = null;
	}

	@Test
	public void returnListOfAllUsers() throws Exception {
		when(userService.findAllUsers()).thenReturn(usrList);
		mockMvc.perform(get("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isAccepted()).andDo(print());
		verify(userService, times(1)).findAllUsers();
	}

	@Test
	public void givenUserIdThenShouldReturnUser() throws Exception {
		when(userService.findById(user.getId())).thenReturn(user);
		mockMvc.perform(get("/api/v1/users/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isOk()).andDo(print());
		verify(userService, times(1)).findById(user.getId());
	}

	@Test
	public void givenUserNameThenShouldUserId() throws Exception {
		when(userService.findByUserName(user.getUserName())).thenReturn(1);
		mockMvc.perform(get("/api/v1/users/userNames/Naveed321")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(user)))
				.andExpect(status().isOk())
				.andDo(print());
		verify(userService, times(1)).findByUserName(user.getUserName());
	}

	@Test
	public void givenUserShouldSaveTheUser() throws Exception {
		when(userService.saveUser(user)).thenReturn(true);
		usrController.add(user);
		verify(userService, times(1)).saveUser(user);
	}

	@Test
	public void givenUserIdShouldReturnUpdatedUser() throws Exception {
		user.setCity("Mangalagiri");
		user.setUserName("Naveed123");
		userService.updateUser(user);
		when(userService.findById(user.getId())).thenReturn(user);
		mockMvc.perform(
				put("/api/v1/users//update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(user)))
				.andExpect(status().isOk())
				.andDo(print());
		verify(userService, times(1)).findById(user.getId());
	}

	@Test
	public void givenUserIdThenUserShouldBeDeleted() throws Exception {
		when(userService.deleteUsersById(user.getId())).thenReturn(user);
		mockMvc.perform(delete("/api/v1/users/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isAccepted()).andDo(print());
		verify(userService, times(1)).deleteUsersById(user.getId());
	}

	public static String asJsonString(final Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}
}
