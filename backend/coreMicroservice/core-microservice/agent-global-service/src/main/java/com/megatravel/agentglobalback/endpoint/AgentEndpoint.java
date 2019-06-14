package com.megatravel.agentglobalback.endpoint;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megatravel.agentglobalback.dto.AgentDTO;
import com.megatravel.agentglobalback.model.Agent;
import com.megatravel.agentglobalback.model.EditRequest;
import com.megatravel.agentglobalback.model.EditResponse;
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
	
	private static final Logger log = LoggerFactory.getLogger(AgentEndpoint.class);
	
	@Autowired
	AgentService agentService;
	
	@Autowired
	NeaktiviranAgentService neaktiviranAgentService;
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAgentRequest")
	@ResponsePayload
	public GetAgentResponse getAgent(@RequestPayload GetAgentRequest request) {
		log.info("getAgent " + request.getId());
		
		GetAgentResponse response = new GetAgentResponse();
		Agent agent = agentService.findOne(request.getId());
		if (agent==null) {
			return response;
		}
		response.setAgent(new AgentDTO(agent));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAgentByEmailRequest")
	@ResponsePayload
	public GetAgentByEmailResponse getAgentByEmail(@RequestPayload GetAgentByEmailRequest request) {
		log.info("getAgentByEmail " + request.getEmail());
		
		GetAgentByEmailResponse response = new GetAgentByEmailResponse();
		Agent agent = agentService.findByEmail(request.getEmail());
		response.setAgent(agent);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginRequest")
	@ResponsePayload
	public LoginResponse login(@RequestPayload LoginRequest request) {
		log.info("login " + request.getAgentPrijavaDTO());
		
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
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "editRequest")
	@ResponsePayload
	public EditResponse edit(@RequestPayload EditRequest request) {
		log.info("edit " + request.getAgent());
		
		EditResponse response = new EditResponse();
		if (request.getAgent()==null) {
			return response;
		}
		
		Agent tempKorisnik = agentService.findByEmail(request.getAgent().getEmail());
		if(tempKorisnik != null) {
			if (tempKorisnik.getIdAgenta()!=request.getAgent().getIdAgenta()) {
				//mora biti jedinstveni mail za korisnika
				return response;
			}
		}
		
		tempKorisnik = agentService.findByPMB(request.getAgent().getPoslovniMaticniBroj());
		if(tempKorisnik != null) {
			if (tempKorisnik.getIdAgenta()!=request.getAgent().getIdAgenta()) {
				//mora biti jedinstveni PMB za korisnika
				return response;
			}
		}
		
		response.setAgent(new AgentDTO(agentService.save(request.getAgent())));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "signUpRequest")
	@ResponsePayload
	public SignUpResponse signUp(@RequestPayload SignUpRequest request) {
		log.info("signUp " + request.getAgentRegistracijaDTO());
		
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
	public void signout(HttpServletRequest request) {
		log.info("signout " + request.getAuthType());
		return;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "validateTokenRequest")
	@ResponsePayload
	public ValidateTokenResponse validateToken(@RequestPayload ValidateTokenRequest request) {
		log.info("validateToken " + request.getToken());
		
		ValidateTokenResponse response = new ValidateTokenResponse();
		response.setValid(new Boolean(true));
		return response;
	}
	
	
}
