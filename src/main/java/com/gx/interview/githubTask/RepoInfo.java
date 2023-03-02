package com.gx.interview.githubTask;

import java.time.OffsetDateTime;


public class RepoInfo {

    private String fullName;
    private String description;
    private String cloneUrl;
    private boolean forked;
    private int forks;
    private OffsetDateTime cratedAt;

    public RepoInfo(String fullName, String description, String cloneUrl, boolean forked, int forks, OffsetDateTime cratedAt) {
        this.fullName = fullName;
        this.description = description;
        this.cloneUrl = cloneUrl;
        this.forked = forked;
        this.forks = forks;
        this.cratedAt = cratedAt;
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
