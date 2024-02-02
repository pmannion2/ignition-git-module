package com.axone_io.ignition.git.commissioning;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IgnitionConfig {
    @Setter
    private String projectName;
    @Setter
    private String userName;
    @Setter
    private boolean inheritable;
    @Setter
    private String parentName;
}
