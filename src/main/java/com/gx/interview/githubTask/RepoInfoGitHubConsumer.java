package com.gx.interview.githubTask;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RepoInfoGitHubConsumer implements RepoInfoConsumer {

    @Value("${github.token}")
    private String token;

    public Optional<RepoInfo> getRepoInfo(String userName, String repoName) {

        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.github.com/repos/" + userName + "/" + repoName;

        HttpEntity<String> request = getHeaders();

        ResponseEntity<RepoInfoResponse> response = restTemplate.exchange(apiUrl, HttpMethod.GET, request, RepoInfoResponse.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return Optional.ofNullable(response.getBody()).map(RepoInfoResponse::toDomain);
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return Optional.empty();
        } else {
            throw new RuntimeException("Failed to fetch data from github");
        }
    }

    public Optional<ReposInfo> getAllUserReposInfo(String userName) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.github.com/users/" + userName + "/repos";

        HttpEntity<String> request = getHeaders();

        ResponseEntity<List<RepoInfoResponse>> response = restTemplate.exchange(apiUrl, HttpMethod.GET, request,
                new ParameterizedTypeReference<List<RepoInfoResponse>>() {
                });
        if (response.getStatusCode().is2xxSuccessful()) {
            return Optional.ofNullable(response.getBody())
                    .map(repoInfoEntities -> repoInfoEntities.stream()
                            .map(RepoInfoResponse::toDomain)
                            .collect(Collectors.toList()))
                    .map(ReposInfo::new);

        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return Optional.empty();
        } else {
            throw new RuntimeException("Failed to fetch data from github");
        }

    }
    private HttpEntity<String> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.github+json");
        headers.set("Authorization", "Bearer " + token);
        headers.set("X-GitHub-Api-Version", "2022-11-28");
        return new HttpEntity<>(headers);
    }

}

