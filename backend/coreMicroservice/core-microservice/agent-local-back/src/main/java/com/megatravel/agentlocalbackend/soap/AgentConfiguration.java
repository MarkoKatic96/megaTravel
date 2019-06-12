package com.megatravel.agentlocalbackend.soap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class AgentConfiguration {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("com.megatravel.agentlocalbackend.wsdl");
		return marshaller;
	}

	@Bean
	@Primary
	public AgentClient agentClient(Jaxb2Marshaller marshaller) {
		AgentClient client = new AgentClient();
		client.setDefaultUri("http://localhost:8400/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
