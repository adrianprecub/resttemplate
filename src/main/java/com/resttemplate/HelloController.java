package com.resttemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by adi on 5/12/18.
 */
@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    @Qualifier(value = "default")
    private RestTemplate restTemplate;

    @RequestMapping("/")
    public String index() {

        String fooResourceUrl = "http://localhost:8080/foo";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);


        return response.getBody();
    }

    @RequestMapping("/foo")
    public String foo() {
        return "Foo";
    }

}
