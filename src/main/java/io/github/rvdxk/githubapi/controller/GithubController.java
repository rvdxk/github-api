package io.github.rvdxk.githubapi.controller;

import io.github.rvdxk.githubapi.exception.ErrorResponse;
import io.github.rvdxk.githubapi.service.GithubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class GithubController {

    private final GithubService githubService;

    @GetMapping("/{username}/repos")
    public ResponseEntity<Object> getUserRepos(@PathVariable String username,
                                               @RequestHeader(value = HttpHeaders.ACCEPT, defaultValue = "application/json") String acceptHeader) {
        if (!"application/json".equals(acceptHeader)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), "Accept header must be 'application/json'"));
        }
        try {
            return ResponseEntity.ok(githubService.getUserRepos(username));
        } catch (Exception e) {
            log.error("An error occurred while fetching repositories for user {}: {}", username, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User not found"));
        }
    }
}