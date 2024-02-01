package com.axone_io.ignition.git.commissioning;

public class ProjectConfig {
    // Getters and setters
    @lombok.Getter
    @lombok.Setter
    private RepoConfig repo;
    @lombok.Getter
    @lombok.Setter
    private IgnitionConfig ignition;
    @lombok.Getter
    @lombok.Setter
    private UserConfig user;
    @lombok.Getter
    @lombok.Setter
    private CommissioningConfig commissioning;
    @lombok.Getter
    @lombok.Setter
    private String initDefaultBranch;

    public RepoConfig getRepo() {
        return repo;
    }

    public void setRepo(RepoConfig repo) {
        this.repo = repo;
    }

    public IgnitionConfig getIgnition() {
        return ignition;
    }

}
