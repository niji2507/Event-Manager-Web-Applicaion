package com.eventmanager.rest.access.backendrestservice.basicAuth;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eventmanager.rest.access.backendrestservice.eventList.EventJPARepository;
import com.eventmanager.rest.access.backendrestservice.response.Response;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class AuthenticationController {
	
	@Autowired
	private UserJpaRepository userJpaRepo;
	
	@Autowired
	private RoleJpaRepository roleRepository;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping(path="/basicauth")
	public AuthenticationBean authenticateUser() {
		
		return new AuthenticationBean("You are authenticated");
	}
	
	@PostMapping(path="/register")
	public ResponseEntity<Response> registerUser(@RequestBody UserInfo user) {
		
		Response response = null;
		boolean isExistingUser = userJpaRepo.findById(user.getUserName()).isPresent();	
		
		if(!isExistingUser) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setRoles(new HashSet<>(roleRepository.findAll()));
			userJpaRepo.save(user);
			response = new Response(true,"Successfully Created user");
			return new ResponseEntity<Response>(response,HttpStatus.OK); 
		}
		
		response = new Response(false,"User Already exists");
		
		return new ResponseEntity<Response>(response,HttpStatus.PRECONDITION_FAILED);
	}
	
	
	

}
