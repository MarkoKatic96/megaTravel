package com.megatravel.agentglobalback.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.megatravel.agentglobalback.model.Agent;
import com.megatravel.agentglobalback.model.TipOsobe;
import com.megatravel.agentglobalback.repository.AgentRepository;

@Service
public class AgentDetails implements UserDetailsService {

	@Autowired
	private AgentRepository agentRepository;

	//izvuce iz baze usere koji nam trebaju i automatksi se aktivira
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Agent agent = agentRepository.findByEmail(email);

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