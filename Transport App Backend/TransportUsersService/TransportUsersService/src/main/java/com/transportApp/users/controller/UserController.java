package com.transportApp.users.controller;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transportApp.users.model.AuthRequest;
import com.transportApp.users.model.Users;
import com.transportApp.users.service.UserService;
import com.transportApp.users.util.JWTUtil;


@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("api/v1/users")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JWTUtil myUtil;

	// Read data
	@GetMapping
	public ResponseEntity<?> users() {
		List<Users> userList = userService.findAllUsers();
		return new ResponseEntity<List<Users>>(userList, HttpStatus.ACCEPTED);
	}

	// Get User using user Id
	@GetMapping("/{userId}")
	public ResponseEntity<?> get(@PathVariable Integer userId) {
		System.out.println("Finding user" + userId);
		try {
			Users users = userService.findById(userId);
			return new ResponseEntity<Users>(users, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<String>("Failure", HttpStatus.NOT_FOUND);
		}
	}

	// Get user by username
	@GetMapping("/userNames/{userName}")
	public ResponseEntity<?> getUserId(@PathVariable String userName) {
		try {
			Integer user = userService.findByUserName(userName);
			return new ResponseEntity<Integer>(user, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<String>("Failure", HttpStatus.CONFLICT);
		}
	}

	// Create User
	@PostMapping("/register")
	public void add(@RequestBody Users usr)
	{	
		System.out.println("New User");
		userService.saveUser(usr);
		
	}
//	public ResponseEntity<String> add(@RequestBody Users usr) {
//		System.out.println("New User");
//		userService.saveUser(usr);
//		return new ResponseEntity<String>("User Added",HttpStatus.OK);
//	}

	// Update User
	@PutMapping("/update/{userId}")
	public ResponseEntity<?> update(@RequestBody Users usr, @PathVariable Integer userId) {
		try {
			Users existUser = userService.findById(userId);
			existUser.setUserName(usr.getUserName());
			existUser.setPassword(usr.getPassword());
			existUser.setCity(usr.getCity());
			existUser.setEmail(usr.getEmail());
			existUser.setPhoneNo(usr.getPhoneNo());
			existUser.setFirstName(usr.getFirstName());
			existUser.setLastName(usr.getLastName());
			existUser.setConfirmPassword(usr.getConfirmPassword());
			userService.updateUser(existUser);
			return new ResponseEntity<Users>(existUser, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<String>("Failure", HttpStatus.NOT_FOUND);
		}

	}

	// Delete User
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> delete(@PathVariable Integer userId) {
		userService.deleteUsersById(userId);
		return new ResponseEntity<String>("User " + userId + " deleted", HttpStatus.ACCEPTED);

	}

	// User login
	@PostMapping("/login")
	public String generateToken(@RequestBody AuthRequest authrequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authrequest.getUserName(), authrequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("Invalid username and password");
		}
		return myUtil.generateToken(authrequest.getUserName());
	}

}
