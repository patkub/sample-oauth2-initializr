package com.example.demo.config;

import com.example.demo.contributor.ProjectFileContributor;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;

@ProjectGenerationConfiguration
public class MainConfig {

    private static final Log logger = LogFactory.getLog(MainConfig.class);

    @Bean
    ProjectFileContributor projectFileContributor() {
        return new ProjectFileContributor();
    }

}
