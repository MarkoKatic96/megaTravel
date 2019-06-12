package io.xws.adminservice.jwt;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenFilterConfigurer //extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>
{

//	private JwtTokenUtils jwtTokenProvider;
//
//	public JwtTokenFilterConfigurer(JwtTokenUtils jwtTokenProvider) {
//		this.jwtTokenProvider = jwtTokenProvider;
//	}
//
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider);
//		http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
//	}

}