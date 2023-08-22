package com.transportApp.users.service;

import java.util.List;

import com.transportApp.users.model.Users;



public interface UserService {

	public boolean saveUser(Users usr);

	public List<Users> findAllUsers();

	public Users deleteUsersById(int id);

	public Users findById(int id);

	public Integer findByUserName(String userName);
	
	public void updateUser(Users usr);
}
