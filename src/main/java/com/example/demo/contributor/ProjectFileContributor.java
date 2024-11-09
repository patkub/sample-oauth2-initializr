package com.example.demo.contributor;

import io.spring.initializr.generator.project.contributor.ProjectContributor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Ordered;

/**
 * A {@link ProjectContributor} that contributes custom files to a project.
 */
public class ProjectFileContributor implements ProjectContributor {

    private static final Log logger = LogFactory.getLog(ProjectFileContributor.class);

    private ProjectFileWriter projectFileWriter;

    public ProjectFileContributor() {
        this.projectFileWriter = new ProjectFileWriter();
    }

    @Override
    public void contribute(Path projectRoot) throws IOException {
        // TODO: remove this example file
        // Create custom path under resources
        Files.createDirectories(projectRoot.resolve("src/main/resources/static/custom"));
        logger.info("Created custom resources path: src/main/resources/static/custom");

        // Copy example file from intializr resources to project resources
        final ProjectFile fileWithContent = new ProjectFile("/fileWithContent.txt", "src/main/resources/static/custom/fileWithContent2.txt");
        this.projectFileWriter.writeResourcesFile(projectRoot, fileWithContent.getSrcPath(), fileWithContent.getOutPath());


        // TODO: process these files as mustache templates
        // TODO: figure out how to set package path
        // Copy projectFiles from intializr resources to project sources
        List<ProjectFile> projectFiles = List.of(
                new ProjectFile("/projectFiles/SecurityConfig.java.mustache", "src/main/java/SecurityConfig.java"),
                new ProjectFile("/projectFiles/Generated.java.mustache", "src/main/java/Generated.java")
        );
        projectFiles.forEach((e) -> this.projectFileWriter.writeResourcesFile(projectRoot, e.getSrcPath(), e.getOutPath()));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 10;
    }

}
