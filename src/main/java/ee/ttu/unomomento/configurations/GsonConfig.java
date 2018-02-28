package ee.ttu.unomomento.configurations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfig {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Bean
    public Gson getInstance() {
        return gson;
    }
}
