package com.gx.interview.githubTask;

import java.util.Optional;

public interface RepoInfoConsumer {

    Optional<RepoInfo> getRepoInfo(String userName, String repoName);
    Optional<ReposInfo> getAllUserReposInfo(String userName);

}
