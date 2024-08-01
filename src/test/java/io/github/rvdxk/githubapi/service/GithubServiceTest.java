package io.github.rvdxk.githubapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.rvdxk.githubapi.client.GithubClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class GithubServiceTest {

    @Mock
    private GithubClient githubClient;

    @InjectMocks
    private GithubService githubService;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserRepository() throws Exception {
        String username = "rvdxk";
        String mockReposJson = "[{\"name\": \"repository1\", \"fork\": false, \"owner\": {\"login\": \"rvdxk\"}, \"branches_url\": \"url\"}]";
        String mockBranchesJson = "[{\"name\": \"main\", \"commit\": {\"sha\": \"abc123\"}}]";

        when(githubClient.getRepos(username)).thenReturn(mockReposJson);
        when(githubClient.getBranches("url")).thenReturn(mockBranchesJson);

        List<Object> repos = githubService.getUserRepos(username);

        assertNotNull(repos);
        assertEquals(1, repos.size());

        ObjectNode repoInfo = (ObjectNode) repos.get(0);
        assertEquals("repository1", repoInfo.get("Repository Name").asText());
        assertEquals("rvdxk", repoInfo.get("Owner Login").asText());

        ArrayNode branchesArray = (ArrayNode) repoInfo.get("Branches");
        assertNotNull(branchesArray);
        assertEquals(1, branchesArray.size());
    }
}