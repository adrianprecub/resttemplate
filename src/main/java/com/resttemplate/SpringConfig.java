package com.resttemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by adi on 5/12/18.
 */
@Configuration
@EnableAutoConfiguration
public class SpringConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Bean
    @Qualifier(value = "default")
    public RestTemplate restTemplateDefault() {
        RestTemplate template = new RestTemplate();
        return template;
    }

    @Bean
    @Qualifier(value = "customizedRestTemplate")
    public RestTemplate customizedRestTemplate() {
//        RestTemplateCustomizer customizer =
        RestTemplateBuilder builder = new RestTemplateBuilder(restTemplateCustomizer());


        RestTemplate template = new RestTemplate();
        return template;
    }


    @Bean
    @Qualifier(value = "restTemplateCustomizer")
    public RestTemplateCustomizer restTemplateCustomizer() {
        RestTemplateCustomizer customizer = new RestTemplateCustomizer() {
            @Override
            public void customize(RestTemplate restTemplate) {
                LOGGER.info("configuration for RestTemplate");
            }
        };

        return customizer;
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

}
