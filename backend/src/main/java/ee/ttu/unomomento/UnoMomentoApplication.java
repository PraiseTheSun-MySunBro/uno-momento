package ee.ttu.unomomento;

import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class UnoMomentoApplication {
	public static void main(String[] args) {
		SpringApplication.run(UnoMomentoApplication.class, args);
	}
}