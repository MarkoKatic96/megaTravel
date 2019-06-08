package com.megatravel.agentlocalbackend.soap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.megatravel.agentlocalbackend.wsdl.AgentPrijavaDTO;
import com.megatravel.agentlocalbackend.wsdl.AgentRegistracijaDTO;
import com.megatravel.agentlocalbackend.wsdl.GetAgentByEmailRequest;
import com.megatravel.agentlocalbackend.wsdl.GetAgentByEmailResponse;
import com.megatravel.agentlocalbackend.wsdl.GetAgentRequest;
import com.megatravel.agentlocalbackend.wsdl.GetAgentResponse;
import com.megatravel.agentlocalbackend.wsdl.LoginRequest;
import com.megatravel.agentlocalbackend.wsdl.LoginResponse;
import com.megatravel.agentlocalbackend.wsdl.SignUpRequest;
import com.megatravel.agentlocalbackend.wsdl.SignUpResponse;
import com.megatravel.agentlocalbackend.wsdl.ValidateTokenRequest;
import com.megatravel.agentlocalbackend.wsdl.ValidateTokenResponse;

public class AgentClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(AgentClient.class);

	public GetAgentResponse getAgent(Long id) {

		GetAgentRequest request = new GetAgentRequest();
		request.setId(id);

		log.info("Requesting agent with id " + id);

		Object o = getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8400/ws/agents", request,
						new SoapActionCallback(
								"https://megatravel.com/GetAgentRequest"));
		GetAgentResponse response =  (GetAgentResponse)o;

		return response;
	}
	
	public GetAgentByEmailResponse getAgentByEmail(String email) {

		GetAgentByEmailRequest request = new GetAgentByEmailRequest();
		request.setEmail(email);

		log.info("Requesting agent with email " + email);

		GetAgentByEmailResponse response = (GetAgentByEmailResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8400/ws/agents", request,
						new SoapActionCallback(
								"https://megatravel.com/GetAgentByEmailRequest"));

		return response;
	}
	
	public LoginResponse getLogin(AgentPrijavaDTO agentPrijavaDTO) {

		LoginRequest request = new LoginRequest();
		request.setAgentPrijavaDTO(agentPrijavaDTO);

		LoginResponse response = (LoginResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8400/ws/agents", request,
						new SoapActionCallback(
								"https://megatravel.com/LoginRequest"));

		return response;
	}
	
	public SignUpResponse getSignUp(AgentRegistracijaDTO agentRegistracijaDTO) {

		SignUpRequest request = new SignUpRequest();
		request.setAgentRegistracijaDTO(agentRegistracijaDTO);

		SignUpResponse response = (SignUpResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8400/ws/agents", request,
						new SoapActionCallback(
								"https://megatravel.com/SignUpRequest"));

		return response;
	}
	
	public ValidateTokenResponse getValidateToken(String token) {

		ValidateTokenRequest request = new ValidateTokenRequest();
		request.setToken(token);

		ValidateTokenResponse response = (ValidateTokenResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8400/ws/agents", request,
						new SoapActionCallback(
								"https://megatravel.com/ValidateTokenRequest"));

		return response;
	}
	
	

}
