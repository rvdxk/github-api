package io.github.rvdxk.githubapi.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class GithubClient {

    private static final String GITHUB_API_URL = "https://api.github.com";
    private final RestTemplate restTemplate;

    public String getRepos(String username) {
        String url = String.format("%s/users/%s/repos", GITHUB_API_URL, username);
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (HttpClientErrorException e) {
            log.error("Error fetching repositories for user {}: {}", username, e.getMessage());
            return null;
        }
    }

    public String getBranches(String repositoryUrl) {
        try {
            return restTemplate.getForObject(repositoryUrl, String.class);
        } catch (HttpClientErrorException e) {
            log.error("Error fetching branches for repository {}: {}", repositoryUrl, e.getMessage());
            return null;
        }
    }
}
