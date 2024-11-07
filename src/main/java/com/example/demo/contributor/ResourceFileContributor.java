package com.example.demo.contributor;

import io.spring.initializr.generator.project.contributor.ProjectContributor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Ordered;

/**
 * A {@link ProjectContributor} that contributes custom files to a project.
 */
public class ResourceFileContributor implements ProjectContributor {

    private static final Log logger = LogFactory.getLog(ResourceFileContributor.class);

    public ResourceFileContributor() {

    }

    @Override
    public void contribute(Path projectRoot) throws IOException {
        // Create custom path under resources
        Files.createDirectories(projectRoot.resolve("src/main/resources/static/custom"));

        // Copy file from intializr resources to project resources
        String resourceFilePath = "/fileWithContent.txt";
        String outputFilePath = "src/main/resources/static/custom/fileWithContent2.txt";
        this.writeResourcesFile(projectRoot, resourceFilePath, outputFilePath);
    }

    /**
     * Write file from resources to project
     * @param projectRoot Path to project root
     * @param srcResourceFilePathStr Path to source file in resources folder (ex. file.txt)
     * @param outputFilePathStr Path to output file in generated project from root (ex. src/main/resources/file.txt)
     */
    public void writeResourcesFile(Path projectRoot, String srcResourceFilePathStr, String outputFilePathStr) {
        try (InputStream inputStream = ResourceFileContributor.class.getResourceAsStream(srcResourceFilePathStr);
             OutputStream outputStream = Files.newOutputStream(projectRoot.resolve(outputFilePathStr))) {

            if (inputStream == null) {
                throw new IOException("Resource file not found: " + srcResourceFilePathStr);
            }

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            logger.info("Copied %s to %s".formatted(srcResourceFilePathStr, outputFilePathStr));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 10;
    }

}
