package com.megatravel.agentglobalback.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.megatravel.agentglobalback.dto.AgentDTO;
import com.megatravel.agentglobalback.model.Agent;
import com.megatravel.agentglobalback.model.GetAgentRequest;
import com.megatravel.agentglobalback.model.GetAgentResponse;
import com.megatravel.agentglobalback.service.AgentService;

@Endpoint
public class AgentEndpoint {

	private static final String NAMESPACE_URI = "https://megatravel.com";
	
	@Autowired
	AgentService agentService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAgentRequest")
	@ResponsePayload
	public GetAgentResponse getAgent(@RequestPayload GetAgentRequest request) {
		GetAgentResponse response = new GetAgentResponse();
		Agent agent = agentService.findOne(request.getId());
		response.setAgent(new AgentDTO(agent));
		return response;
	}
}
