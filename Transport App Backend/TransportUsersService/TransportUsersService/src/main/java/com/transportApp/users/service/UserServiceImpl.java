package com.transportApp.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transportApp.users.model.Users;
import com.transportApp.users.repository.UserRepo;



@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public boolean saveUser(Users usr) {
		Users user = userRepo.save(usr);
		if(user!=null) {
			return true;
		}
			return false;
	}

	@Override
	public List<Users> findAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public Users deleteUsersById(int id) {
		Users user = userRepo.findById(id).get();
		userRepo.delete(user);
		return user;
	
	}

	@Override
	public Users findById(int id) {
		Users userRec=userRepo.findById(id).get();  
		if(userRec!=null)	{	
			return userRec;
		}
		return null;
	}

	@Override
	public void updateUser(Users usr) {
		userRepo.save(usr);
		
	}

	@Override
	public Integer findByUserName(String userName) {
		Users user = userRepo.findByUserName(userName);
		return user.getId();
	}

}
