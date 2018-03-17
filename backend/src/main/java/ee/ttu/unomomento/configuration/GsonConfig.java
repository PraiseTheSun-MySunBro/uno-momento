package ee.ttu.unomomento.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ee.ttu.unomomento.annotation.AnnotationExclusionDeserializationStrategy;
import ee.ttu.unomomento.annotation.AnnotationExclusionSerializationStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({Gson.class})
public class GsonConfig {
    private static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .addSerializationExclusionStrategy(new AnnotationExclusionSerializationStrategy())
            .addDeserializationExclusionStrategy(new AnnotationExclusionDeserializationStrategy())
            .create();

    @Bean
    public Gson getGson() {
        return gson;
    }
}
