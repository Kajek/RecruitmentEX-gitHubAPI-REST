package com.gx.interview.githubTask;

import java.util.Optional;

public interface RepoInfoRepository {

    Optional<RepoInfo> getRepoInfo(String userName, String repoName);
    Optional<ReposInfo> getAllUserReposInfo(String userName);

}
