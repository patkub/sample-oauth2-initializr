package com.example.demo.contributor;

import io.spring.initializr.generator.io.template.MustacheTemplateRenderer;
import io.spring.initializr.generator.io.text.MustacheSection;

import java.util.Map;

public class ProjectFile {

    private String templateName;
    private String outPath;
    private Map<String, Object> keys;

    private final MustacheTemplateRenderer renderer = new MustacheTemplateRenderer("classpath:/projectFiles");

    /**
     * Describe a project file
     * @param templateName Mustache template source file under resources/projectFiles/
     * @param outPath Path to output file in generated project from root (ex. src/main/resources/file.txt)
     * @param keys Map of mustache key/value pairs
     */
    public ProjectFile(String templateName, String outPath, Map<String, Object> keys) {
        this.templateName = templateName;
        this.outPath = outPath;
        this.keys = keys;
    }

    /**
     * Get mustache section
     * @return MustacheSection
     */
    public MustacheSection getMustacheSection() {
        return new MustacheSection(this.renderer, this.templateName, this.keys);
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    public Map<String, Object> getKeys() {
        return keys;
    }

    public void setKeys(Map<String, Object> keys) {
        this.keys = keys;
    }

    @Override
    public String toString() {
        return "ProjectFile{" +
                "templateName='" + templateName + '\'' +
                ", outPath='" + outPath + '\'' +
                '}';
    }
}
