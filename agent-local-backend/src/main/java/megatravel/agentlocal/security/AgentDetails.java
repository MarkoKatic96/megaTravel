package megatravel.agentlocal.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import megatravel.agentlocal.model.AgentModel;
import megatravel.agentlocal.repository.AgentRepository;
import megatravel.agentlocal.validation.StaticData;

@Service
public class AgentDetails implements UserDetailsService {

	@Autowired
	private AgentRepository agentRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AgentModel agent = agentRepository.findByEmail(email);

		if (agent == null) {
			throw new UsernameNotFoundException("Agent '" + email + "' not found");
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		grantedAuthorities.add(new SimpleGrantedAuthority(StaticData.tipAgenta));

		return new org.springframework.security.core.userdetails.User(email, agent.getLozinka(), true, true, true,
				true, grantedAuthorities);
	}

}