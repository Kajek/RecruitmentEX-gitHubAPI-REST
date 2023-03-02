package com.gx.interview.githubTask;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RepoInfoEntity {

    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("clone_url")
    private String cloneUrl;
    @JsonProperty("fork")
    private boolean forked;
    @JsonProperty("forks")
    private int forks;
    @JsonProperty("created_at")
    private OffsetDateTime cratedAt;

    public RepoInfo toDomain(){
        return new RepoInfo(this.fullName, this.description, this.cloneUrl,
                this.forked, this.forks,this.cratedAt);
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
