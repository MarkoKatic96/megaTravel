package bezbednost.etapa2.appStarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"bezbednost.etapa2.model"}) 
public class AdminZaBezbednostApp {

	public static void main(String[] args) {
		SpringApplication.run(AdminZaBezbednostApp.class, args);

	}

}
