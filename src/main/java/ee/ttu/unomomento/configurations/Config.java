package ee.ttu.unomomento.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;
import java.util.ResourceBundle;

@Configuration
public class Config {
    private Locale locale = new Locale("ee", "EE");

    @Bean
    public ResourceBundle getBundle() {
        return ResourceBundle.getBundle("MessagesBundle", locale);
    }
}
