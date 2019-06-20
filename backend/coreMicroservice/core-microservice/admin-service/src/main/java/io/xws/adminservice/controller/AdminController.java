package io.xws.adminservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.xws.adminservice.dto.AdminDTO;
import io.xws.adminservice.dto.LoginDTO;
import io.xws.adminservice.dto.RegisterDTO;
import io.xws.adminservice.model.Admin;
import io.xws.adminservice.service.IAdminService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin-service/admin")
@Api(tags = "")
public class AdminController 
{
	@Autowired
	private IAdminService adminService;
	
	/*
	 * Logovanje admina
	 */
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO login)
	{
		System.out.println("adminLogin()");
		
		Admin admin = adminService.findByEmail(login.getEmail());
		
		if(admin == null)
			return new ResponseEntity<>("bad req 1", HttpStatus.BAD_REQUEST);

		
		try
		{
			String jwt = adminService.signin(login.getEmail(), login.getLozinka());
			
			return (jwt == null) ? new ResponseEntity<String>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(jwt, HttpStatus.OK);

		}
		catch (Exception e) { //baca exc
			return new ResponseEntity<>("bad req 2", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/*
	 * Registrovanje admina
	 */
	@PostMapping("/register")
	public ResponseEntity<AdminDTO> register(@RequestBody RegisterDTO registracija)
	{
		System.out.println("register()");
		
		AdminDTO adminRegister = adminService.register(registracija);
		
		if(adminRegister.getIme().equals("passerr"))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else if(adminRegister.getIme().equals("uniqueerr"))
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		else
			return new ResponseEntity<AdminDTO>(adminRegister, HttpStatus.CREATED);
	}
	
	
}
