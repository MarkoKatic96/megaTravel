package com.megatravel.agentglobalback.endpoint;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megatravel.agentglobalback.dto.AgentDTO;
import com.megatravel.agentglobalback.model.Agent;
import com.megatravel.agentglobalback.model.GetAgentByEmailRequest;
import com.megatravel.agentglobalback.model.GetAgentByEmailResponse;
import com.megatravel.agentglobalback.model.GetAgentRequest;
import com.megatravel.agentglobalback.model.GetAgentResponse;
import com.megatravel.agentglobalback.model.LoginRequest;
import com.megatravel.agentglobalback.model.LoginResponse;
import com.megatravel.agentglobalback.model.NeaktiviranAgent;
import com.megatravel.agentglobalback.model.SignUpRequest;
import com.megatravel.agentglobalback.model.SignUpResponse;
import com.megatravel.agentglobalback.model.ValidateTokenRequest;
import com.megatravel.agentglobalback.model.ValidateTokenResponse;
import com.megatravel.agentglobalback.service.AgentService;
import com.megatravel.agentglobalback.service.NeaktiviranAgentService;

@Endpoint
public class AgentEndpoint {

	private static final String NAMESPACE_URI = "https://megatravel.com";
	
	@Autowired
	AgentService agentService;
	
	@Autowired
	NeaktiviranAgentService neaktiviranAgentService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAgentRequest")
	@ResponsePayload
	public GetAgentResponse getAgent(@RequestPayload GetAgentRequest request) {
		GetAgentResponse response = new GetAgentResponse();
		Agent agent = agentService.findOne(request.getId());
		response.setAgent(new AgentDTO(agent));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAgentByEmailRequest")
	@ResponsePayload
	public GetAgentByEmailResponse getAgentByEmail(@RequestPayload GetAgentByEmailRequest request) {
		GetAgentByEmailResponse response = new GetAgentByEmailResponse();
		Agent agent = agentService.findByEmail(request.getEmail());
		response.setAgent(agent);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginRequest")
	@ResponsePayload
	public LoginResponse login(@RequestPayload LoginRequest request) {
		LoginResponse response = new LoginResponse();
		Agent agent = agentService.findByEmail(request.getAgentPrijavaDTO().getEmail());
		if(agent == null) {
			response.setJwt("");
			return response;
		}
		try {
			String jwt = agentService.signin(request.getAgentPrijavaDTO().getEmail(), request.getAgentPrijavaDTO().getLozinka());
			ObjectMapper mapper = new ObjectMapper();
			response.setJwt(mapper.writeValueAsString(jwt));
			return response;
		} catch (Exception e) {
			response.setJwt("");
			return response;
		}
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "signUpRequest")
	@ResponsePayload
	public SignUpResponse signUp(@RequestPayload SignUpRequest request) {
		SignUpResponse response = new SignUpResponse();
		Agent tempKorisnik = agentService.findByEmail(request.getAgentRegistracijaDTO().getEmail());
		if(tempKorisnik != null) {
			//mora biti jedinstveni mail za korisnika
			return response;
		}
		
		tempKorisnik = agentService.findByPMB(request.getAgentRegistracijaDTO().getPoslovniMaticniBroj());
		if(tempKorisnik != null) {
			//mora biti jedinstveni PIM za korisnika
			return response;
		}
		
		NeaktiviranAgent tempKorisnik2 = neaktiviranAgentService.findByEmail(request.getAgentRegistracijaDTO().getEmail());
		if(tempKorisnik2 != null) {
			//mora biti jedinstveni mail za korisnika
			return response;
		}
		
		tempKorisnik2 = neaktiviranAgentService.findByPMB(request.getAgentRegistracijaDTO().getPoslovniMaticniBroj());
		if(tempKorisnik2 != null) {
			//mora biti jedinstveni PIM za korisnika
			return response;
		}
		
		NeaktiviranAgent agent = new NeaktiviranAgent(null, request.getAgentRegistracijaDTO().getIme(), request.getAgentRegistracijaDTO().getPrezime(), request.getAgentRegistracijaDTO().getPoslovniMaticniBroj(), request.getAgentRegistracijaDTO().getEmail());
		NeaktiviranAgent retValue = neaktiviranAgentService.save(agent);
		response.setNeaktiviranAgent(retValue);
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "signoutRequest")
	@ResponsePayload
	public void signout(HttpServletRequest req) {
		return;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "validateTokenRequest")
	@ResponsePayload
	public ValidateTokenResponse validateToken(@RequestPayload ValidateTokenRequest request) {
		ValidateTokenResponse response = new ValidateTokenResponse();
		response.setValid(new Boolean(true));
		return response;
	}
	
	
}
