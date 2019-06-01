package com.megatravel.smestajservice.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.megatravel.smestajservice.model.Agent;
import com.megatravel.smestajservice.model.TipOsobe;

@Service
public class AgentDetails implements UserDetailsService {

	@Autowired
	private RestTemplate restTemplate;

	//izvuce iz baze usere koji nam trebaju i automatksi se aktivira
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		ResponseEntity<Agent> agentEntity = restTemplate.getForEntity("http://agent-global-service/agent/e/"+email, Agent.class);
		if (agentEntity.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		
		Agent agent = agentEntity.getBody();

		if (agent == null) {
			//throw new UsernameNotFoundException("Agent '" + email + "' not found");
			return null;
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		grantedAuthorities.add(new SimpleGrantedAuthority(TipOsobe.AGENT.toString()));

		return new org.springframework.security.core.userdetails.User(email, agent.getLozinka(), true, true, true,
				true, grantedAuthorities);
	}

}