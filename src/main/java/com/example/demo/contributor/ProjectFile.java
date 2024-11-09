package com.example.demo.contributor;

public class ProjectFile {

    private String srcPath;
    private String outPath;

    /**
     * Describe a project file
     * @param srcPath Path to source file in resources folder (ex. file.txt)
     * @param outPath Path to output file in generated project from root (ex. src/main/resources/file.txt)
     */
    public ProjectFile(String srcPath, String outPath) {
        this.srcPath = srcPath;
        this.outPath = outPath;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    @Override
    public String toString() {
        return "ProjectFile{" +
                "srcPath='" + srcPath + '\'' +
                ", outPath='" + outPath + '\'' +
                '}';
    }
}
