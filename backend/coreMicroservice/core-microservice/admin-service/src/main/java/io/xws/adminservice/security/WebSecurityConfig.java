package io.xws.adminservice.security;

import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig //extends WebSecurityConfigurerAdapter 
{

//	@Autowired
//	private JwtTokenUtils jwtTokenProvider;
//
//	@Autowired
//	private UserDetailsService userDetailsService;
//
//	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
//	}
//
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		// Disable CSRF (cross site request forgery)
//		http.csrf().disable();
//
//		// No session will be created or used by spring security
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//		//TODO 1: ono cemu neregistrovani korisnik sme da pristupi
////		http.authorizeRequests().antMatchers("/api/*", "admin/login", "/admin/login", "/admin/register").permitAll()
////		.antMatchers(HttpMethod.PUT, "/api/comment/*").permitAll().anyRequest()
////				.authenticated();
//
//		
//		// If a user try to access a resource without having enough permissions
//		http.exceptionHandling().accessDeniedPage("/api/admin/login");
//
//		// Apply JWT
//		http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
//	}
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		//sve sto ce se ignorisati za web
//		web.ignoring().antMatchers("/webjars/**")//
//				.antMatchers("/public")//
//				.antMatchers("/main**")//
//				.antMatchers("/inline**")//
//				.antMatchers("/polyfills**")//
//				.antMatchers("/styles**")//
//				.antMatchers("/favicon.ico")//
//				.antMatchers("/scripts**")//
//				.antMatchers("/glyphicons**")//
//				.antMatchers("/fontawesome**")//
//				.antMatchers("/vendor**")//
//				.antMatchers("/assets/**")//
//				.antMatchers("/Poppins**")//
//				.antMatchers("/h2-console");
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder(12);
//	}
	
}