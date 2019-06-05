package com.telran.resttemplate;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Object> entity = restTemplate.postForEntity("url", request, response);
//        restTemplate.put("url", request);

        HttpEntity<String> entity = new HttpEntity<>("this is my text"); //entity: body & headers

        ResponseEntity<String> responseEntity = restTemplate.exchange("url", HttpMethod.PUT, entity, String.class);
    }
}
