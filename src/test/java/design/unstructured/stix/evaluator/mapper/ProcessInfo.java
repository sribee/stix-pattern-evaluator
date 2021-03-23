package design.unstructured.stix.evaluator.mapper;

import design.unstructured.stix.evaluator.EvaluatorFeatures;
import design.unstructured.stix.evaluator.mapper.annotations.StixObject;
import design.unstructured.stix.evaluator.mapper.annotations.StixProperty;

/**
 * ProcessInfo
 */
@StixObject
public class ProcessInfo {

    private String name;

    private String path;

    @StixProperty(name = "forward_path", features = { EvaluatorFeatures.BACKSLASH_AS_FORWARDSLASH })
    private String forwardPath;

    @StixProperty(name = { "pid", "id" })
    private Integer remapToId;

    private String commandLine;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getRemapToId() {
        return remapToId;
    }

    public void setRemapToId(Integer pid) {
        this.remapToId = pid;
    }

    public String getCommandLine() {
        return commandLine;
    }

    public void setCommandLine(String commandLine) {
        this.commandLine = commandLine;
    }

    public String getForwardPath() {
        return forwardPath;
    }

    public void setForwardPath(String forwardPath) {
        this.forwardPath = forwardPath;
    }

}