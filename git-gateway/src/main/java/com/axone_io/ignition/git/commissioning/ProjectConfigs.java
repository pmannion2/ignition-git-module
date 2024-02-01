package com.axone_io.ignition.git.commissioning;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectConfigs {
    @Setter
    private List<ProjectConfig> projects;
}
