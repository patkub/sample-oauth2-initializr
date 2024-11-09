package com.example.demo.contributor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProjectFileWriter {

    private static final Log logger = LogFactory.getLog(ProjectFile.class);

    /**
     * Write file from resources to project
     * @param projectRoot Path to project root
     * @param srcResourceFilePathStr Path to source file in resources folder (ex. file.txt)
     * @param outputFilePathStr Path to output file in generated project from root (ex. src/main/resources/file.txt)
     */
    public void writeResourcesFile(Path projectRoot, String srcResourceFilePathStr, String outputFilePathStr) {
        try (InputStream inputStream = ProjectFileContributor.class.getResourceAsStream(srcResourceFilePathStr);
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

}
