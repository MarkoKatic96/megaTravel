package megatravel.bezbednost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
@EnableAsync
public class BezbednostApiApp {

	public static void main(String[] args) {
		SpringApplication.run(BezbednostApiApp.class, args);

	}

}
