package com.axone_io.ignition.git.commissioning;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommissioningConfig {
    // Getters and setters
    private boolean importThemes;
    private boolean importTags;
    private boolean importImages;

    public void setImportThemes(boolean importThemes) {
        this.importThemes = importThemes;
    }

    public void setImportTags(boolean importTags) {
        this.importTags = importTags;
    }

    public void setImportImages(boolean importImages) {
        this.importImages = importImages;
    }
}
