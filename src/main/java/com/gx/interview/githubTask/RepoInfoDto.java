package com.gx.interview.githubTask;

import java.time.OffsetDateTime;

public class RepoInfoDto {

    private String fullName;
    private String description;
    private String cloneUrl;
    private boolean forked;
    private int forks;
    private OffsetDateTime cratedAt;

    public RepoInfoDto(String fullName, String description, String cloneUrl, boolean forked, int forks, OffsetDateTime cratedAt) {
        this.fullName = fullName;
        this.description = description;
        this.cloneUrl = cloneUrl;
        this.forked = forked;
        this.forks = forks;
        this.cratedAt = cratedAt;
    }

    public static RepoInfoDto fromDomain(RepoInfo repoInfo){
        return new RepoInfoDto(repoInfo.getFullName(), repoInfo.getDescription(), repoInfo.getCloneUrl(),
                repoInfo.isForked(), repoInfo.getForks(),repoInfo.getCratedAt());
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public boolean isForked() {
        return forked;
    }

    public int getForks() {
        return forks;
    }

    public OffsetDateTime getCratedAt() {
        return cratedAt;
    }
}
