package by.ita.je.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    private static final String SERVICE_APP_URL = "http://127.0.0.1:8111";

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder()
                .rootUri(SERVICE_APP_URL)
                .build();
    }
}
