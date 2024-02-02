package com.axone_io.ignition.git.commissioning;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectConfigs {
    private List<ProjectConfig> projects;
    // Method to Add Project to Class
    public void addProject(ProjectConfig projectConfig) {
        if (this.projects == null) { this.projects = new java.util.ArrayList<ProjectConfig>();}
        projects.add(projectConfig);
}
}
