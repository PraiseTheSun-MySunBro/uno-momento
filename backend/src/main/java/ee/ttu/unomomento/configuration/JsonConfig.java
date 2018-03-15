package ee.ttu.unomomento.configuration;

import org.jooq.JSONFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {
    private static final JSONFormat JSON_FORMAT = new JSONFormat().recordFormat(JSONFormat.RecordFormat.OBJECT).header(false);

    @Bean
    public JSONFormat getJSONFormat () {
        return JSON_FORMAT;
    }
}
