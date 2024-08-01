package io.github.rvdxk.githubapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.rvdxk.githubapi.client.GithubClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GithubService {

    private final GithubClient githubClient;
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Object> getUserRepos(String username) throws Exception {
        String repos = githubClient.getRepos(username);
        JsonNode reposNode = mapper.readTree(repos);
        List<Object> filteredRepos = new ArrayList<>();

        for (JsonNode repoNode : reposNode) {
            if (!repoNode.get("fork").asBoolean()) {

                ObjectNode repoInfo = mapper.createObjectNode();

                repoInfo.put("Repository Name", repoNode.get("name").asText());
                repoInfo.put("Owner Login", repoNode.get("owner").get("login").asText());

                String branchesUrl = repoNode.get("branches_url").asText().replace("{/branch}", "");
                String branches = githubClient.getBranches(branchesUrl);

                JsonNode branchesNode = mapper.readTree(branches);
                ArrayNode branchesArray = mapper.createArrayNode();

                for (JsonNode branchNode : branchesNode) {
                    ObjectNode branchInfo = mapper.createObjectNode();
                    branchInfo.put("name", branchNode.get("name").asText());
                    branchInfo.put("last_commit_sha", branchNode.get("commit").get("sha").asText());
                    branchesArray.add(branchInfo);
                }

                repoInfo.set("Branches", branchesArray);
                filteredRepos.add(repoInfo);
            }
        }

        return filteredRepos;
    }
}
