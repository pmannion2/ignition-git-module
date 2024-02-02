package com.axone_io.ignition.git.commissioning;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Getter
@Setter
public class UserConfig {
    // Getters and setters
    private String name;
    private String email;
    private String password;
    @Getter
    @Setter
    private String sshKey;


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSshKey(String sshKey) {
        this.sshKey = sshKey;
    }

    public void setSecretFromFilePath(Path filePath, boolean isSSHAuth) throws IOException {
        if (filePath.toFile().exists() && filePath.toFile().isFile()) {
            String secret = Files.readString(filePath, StandardCharsets.UTF_8);
            if (isSSHAuth) {
                this.sshKey = secret;
            } else {
                this.password = secret;
            }
        }
    }

}
