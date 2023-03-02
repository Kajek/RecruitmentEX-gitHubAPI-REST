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
public class MyApiClient implements RepoInfoRepository {

    @Value("${github.token}")
    private String token;

    public Optional<RepoInfo> getRepoInfo(String userName, String repoName) {

        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.github.com/repos/" + userName + "/" + repoName;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.github+json");
        headers.set("Authorization", "Bearer "+ token);
        headers.set("X-GitHub-Api-Version", "2022-11-28");
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<RepoInfoEntity> response = restTemplate.exchange(apiUrl, HttpMethod.GET, request, RepoInfoEntity.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return Optional.ofNullable(response.getBody()).map(RepoInfoEntity::toDomain);
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return Optional.empty();
        } else {
            throw new RuntimeException("Failed to fetch data from github");
        }
    }

    public Optional<ReposInfo> getAllUserReposInfo(String userName) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.github.com/users/" + userName + "/repos";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.github+json");
        headers.set("Authorization", "Bearer "+token);
        headers.set("X-GitHub-Api-Version", "2022-11-28");
        HttpEntity<String> request = new HttpEntity<>(headers);


        ResponseEntity<List<RepoInfoEntity>> response = restTemplate.exchange(apiUrl, HttpMethod.GET, request,
                new ParameterizedTypeReference<List<RepoInfoEntity>>() {
                });
        if (response.getStatusCode().is2xxSuccessful()) {
            return Optional.ofNullable(response.getBody())
                    .map(repoInfoEntities -> repoInfoEntities.stream()
                            .map(RepoInfoEntity::toDomain)
                            .collect(Collectors.toList()))
                    .map(ReposInfo::new);

        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return Optional.empty();
        } else {
            throw new RuntimeException("Failed to fetch data from github");
        }

    }

}

