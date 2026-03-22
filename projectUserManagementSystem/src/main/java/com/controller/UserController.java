package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.service.UserService;



@RestController
@RequestMapping("/users")
public class UserController {

	
	
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user){
		
		
		User savedUser = userService.registerEmployee(user);
		
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}
	
	
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) {

	    User loggedUser = userService.login(user.getEmail(), user.getPassword());

	    
	    
	    return new ResponseEntity<>(loggedUser, HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("/{id}")
	  public ResponseEntity<User> getProfile(@PathVariable Long id){
		
		User user = userService.getProfile(id);
		
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	
	
	
	@PutMapping("update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
		
		
		User updateUser = userService.updateUser(id, user);
		
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}
	
	
	
	
	@PutMapping("/changePassword/{id}")
	public ResponseEntity<String> changePassword(@PathVariable Long id, @RequestBody User user) {

	    userService.changePassword(id, user.getPassword());

	    
	    return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
	}
	
	
	
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll() {

        List<User> users = userService.getAllEmployees();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
	
	
    
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        userService.deleteEmployee(id);

        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
	
	
	
	
}
