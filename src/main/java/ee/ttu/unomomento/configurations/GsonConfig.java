package ee.ttu.unomomento.configurations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfig {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Bean
    private static Gson getInstance() {
        return gson;
    }
}
