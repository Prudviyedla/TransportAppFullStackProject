package com.transportApp.users.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.transportApp.users.model.Users;
import com.transportApp.users.repository.UserRepo;



@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepo myrepo;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user=myrepo.findByUserName(username);
		return new User(user.getUserName(), user.getPassword(), new ArrayList());
	}

}
