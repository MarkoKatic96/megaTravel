package com.megatravel.agentglobalback.security;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*");
	}
	
	@Bean(name = "agents")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema agentServiceSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("AgentServicePort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("https://megatravel.com");
		wsdl11Definition.setSchema(agentServiceSchema);
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema agentServiceSchema() {
		return new SimpleXsdSchema(new ClassPathResource("agentService.xsd"));
	}
}