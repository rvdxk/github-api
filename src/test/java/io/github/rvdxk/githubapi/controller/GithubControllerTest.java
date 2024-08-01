package io.github.rvdxk.githubapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.rvdxk.githubapi.exception.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GithubControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetUserRepos_NotFound() {
        String username = "asdfgqwerty90123";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        ResponseEntity<String> response = restTemplate.exchange(
                "/users/" + username + "/repos",
                HttpMethod.GET,
                new org.springframework.http.HttpEntity<>(headers),
                String.class
        );

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        String responseBody = response.getBody();
        assertNotNull(responseBody);

        ObjectMapper objectMapper = new ObjectMapper();
        ErrorResponse errorResponse = null;
        try {
            errorResponse = objectMapper.readValue(responseBody, ErrorResponse.class);
        } catch (JsonProcessingException e) {
        }

        assertNotNull(errorResponse);
        assertEquals(HttpStatus.NOT_FOUND.value(), errorResponse.getStatus());
        assertEquals("User not found", errorResponse.getMessage());
    }

    @Test
    void testGetUserRepos_NotAcceptable() {
        String username = "rvdxk";

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<String> response = restTemplate.exchange(
                "/users/" + username + "/repos",
                HttpMethod.GET,
                new org.springframework.http.HttpEntity<>(headers),
                String.class
        );

        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
        String responseBody = response.getBody();
        assertNotNull(responseBody);
    }
}