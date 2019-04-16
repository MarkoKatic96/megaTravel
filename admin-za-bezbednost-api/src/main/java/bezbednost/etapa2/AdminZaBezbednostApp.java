package bezbednost.etapa2;
//https://www.baeldung.com/role-and-privilege-for-spring-security-registration
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"bezbednost.etapa2.model"}) 
public class AdminZaBezbednostApp {

	public static void main(String[] args) {
		SpringApplication.run(AdminZaBezbednostApp.class, args);

	}

}
