package com.megatravel.porukeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.megatravel.porukeservice.jwt.JwtTokenUtils;
import com.megatravel.porukeservice.service.PorukeService;

@RestController
@RequestMapping("/poruke")
public class PorukeController {
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@Autowired
	PorukeService porukeService;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	
}
