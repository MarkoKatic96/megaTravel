package com.hrboot.hrsupport;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HrsupportApplicationTests {
	
	@MockBean
	private RestTemplate template;

	@Test
	public void contextLoads() {
		
		
	}

}
