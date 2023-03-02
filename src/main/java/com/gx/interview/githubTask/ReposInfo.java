package com.gx.interview.githubTask;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ReposInfo {

    private List<RepoInfo> repoInfoList;

    public ReposInfo(List<RepoInfo> repoInfoDtoList) {
        this.repoInfoList = repoInfoDtoList;
    }

    public List<RepoInfo> findWithMatchingName(Pattern regex) {

        return repoInfoList.stream().filter(repoInfoEntity -> regex.matcher(repoInfoEntity.getFullName()).matches())
                .collect(Collectors.toList());
    }

    public Optional<RepoInfo> findOldestRepo() {

        return repoInfoList.stream().sorted(Comparator.comparing(RepoInfo::getCratedAt)).findFirst();

    }

    public Optional<RepoInfo> findMostForkedRepo() {
        int mostForks = 0;
        for (int i = 0; i < repoInfoList.size(); i++) {
            if (repoInfoList.get(i).getForks() > mostForks) {
                mostForks = repoInfoList.get(i).getForks();
            }
        }

        int finalMostForks = mostForks;

        if (repoInfoList.stream().filter(repoInfo -> repoInfo.getForks() == finalMostForks).count() > 1) {
            return repoInfoList.stream().filter(repoInfo -> repoInfo.getForks() == finalMostForks)
                    .sorted(Comparator.comparing(RepoInfo::getCratedAt).reversed()).findFirst();
        }

        return repoInfoList.stream().filter(repoInfo -> repoInfo.getForks() == finalMostForks).findAny();
    }
}

