package com.gx.interview.githubTask;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
public class RepoInfoController {


    private final RepoInfoConsumer repoInfoConsumer;

    @Autowired
    public RepoInfoController(RepoInfoConsumer repoInfoConsumer) {
        this.repoInfoConsumer = repoInfoConsumer;
    }

    @GetMapping("/repositories/{owner}/{repositoryName}")
    public Optional<RepoInfoDto> getRepoInfo(@PathVariable String owner,
                                             @PathVariable String repositoryName) {

        Optional<RepoInfo> tempRepoInfo = repoInfoConsumer.getRepoInfo(owner, repositoryName);
        return tempRepoInfo.map(RepoInfoDto::fromDomain);
    }

    @GetMapping("/repositories-matching/{owner}/{regex}")
    public Optional<List<RepoInfoDto>> getOwnerRepositoriesMatching(@PathVariable String owner,
                                                                    @PathVariable String regex) {

        Pattern pattern = Pattern.compile(regex);

        Optional<ReposInfo> tempRepoInfo = repoInfoConsumer.getAllUserReposInfo(owner);

        return tempRepoInfo
                .map(repoInfos -> repoInfos.findWithMatchingName(pattern))
                .map(repoInfos -> repoInfos.stream().map(RepoInfoDto::fromDomain).collect(Collectors.toList()));

    }

    @GetMapping("/repositories-oldest/{owner}")
    public Optional<RepoInfoDto> getOwnerOldestRepository(@PathVariable String owner) {

        return repoInfoConsumer.getAllUserReposInfo(owner).flatMap(ReposInfo::findOldestRepo)
                .map(RepoInfoDto::fromDomain);
    }

    @GetMapping("/repositories-most-forked/{owner}")
    public Optional<RepoInfoDto> getMostForkedRepository(@PathVariable String owner){

        return repoInfoConsumer.getAllUserReposInfo(owner).flatMap(ReposInfo::findMostForkedRepo)
                .map(RepoInfoDto::fromDomain);
    }
}
