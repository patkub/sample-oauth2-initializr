package com.example.demo.contributor;

import io.spring.initializr.generator.project.contributor.ProjectContributor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.core.Ordered;

/**
 * A {@link ProjectContributor} that contributes custom files to a project.
 */
public class ResourceFileContributor implements ProjectContributor {

    public ResourceFileContributor() {

    }

    @Override
    public void contribute(Path projectRoot) throws IOException {
        Files.createDirectories(projectRoot.resolve("src/main/resources/static/custom"));
        Files.createFile(projectRoot.resolve("src/main/resources/static/custom/someCustomFile.txt"));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 10;
    }

}
