package com.example.demo.contributor;

import io.spring.initializr.generator.io.text.MustacheSection;
import io.spring.initializr.generator.project.contributor.ProjectContributor;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Ordered;

/**
 * A {@link ProjectContributor} that contributes custom files to a project.
 */
public class ProjectFileContributor implements ProjectContributor {

    private static final Log logger = LogFactory.getLog(ProjectFileContributor.class);

    @Override
    public void contribute(Path projectRoot) {
        // TODO: get package path and write to proper location

        // SecurityConfig mustache key/value pairs
        HashMap<String, Object> securityConfigModel = new HashMap<>();
        // TODO: set package path
        securityConfigModel.put("packagePath", "");
        securityConfigModel.put("permittedPaths", "\"/\", \"/images/**\", \"/css/**\"");

        HashMap<String, Object> generatedModel = new HashMap<>();
        // TODO: set package path
        generatedModel.put("packagePath", "");

        List<ProjectFile> projectFiles = List.of(
            new ProjectFile("SecurityConfig.java", "src/main/java/SecurityConfig.java", securityConfigModel),
            new ProjectFile("Generated.java", "src/main/java/Generated.java", generatedModel)
        );
        projectFiles.forEach((e) -> {
            MustacheSection mustacheSection = e.getMustacheSection();
            // Path relative to projectRoot
            String projectFilePath = String.valueOf(projectRoot.resolve(e.getOutPath()));

            // Write filled out mustache template to file
            try (FileOutputStream outputStream = new FileOutputStream(projectFilePath)) {
                PrintWriter writer = new PrintWriter(outputStream, true);
                mustacheSection.write(writer);
                writer.close();
                logger.info("Wrote project file: %s".formatted(e.getOutPath()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 10;
    }

}
