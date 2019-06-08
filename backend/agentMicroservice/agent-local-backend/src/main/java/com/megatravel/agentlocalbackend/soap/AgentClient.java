package com.megatravel.agentlocalbackend.soap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.megatravel.agentlocalbackend.wsdl.GetAgentRequest;
import com.megatravel.agentlocalbackend.wsdl.GetAgentResponse;

public class AgentClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(AgentClient.class);

	public GetAgentResponse getAgent(Long id) {

		GetAgentRequest request = new GetAgentRequest();
		request.setId(id);

		log.info("Requesting agent with id " + id);

		GetAgentResponse response = (GetAgentResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8400/ws/agents", request,
						new SoapActionCallback(
								"https://megatravel.com/GetAgentRequest"));

		return response;
	}

}
