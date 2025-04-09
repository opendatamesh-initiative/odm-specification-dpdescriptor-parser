package org.opendatamesh.dpds.datastoreapi.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiDatabaseServiceVisitor;
import org.opendatamesh.dpds.model.core.ComponentBase;

import java.util.List;

public class DatastoreApiVariableObject extends ComponentBase {
    private String description;
    @JsonProperty("enum")
    private List<String> _enum;
    @JsonProperty("default")
    private String _default;
    private List<String> examples;

    public DatastoreApiVariableObject() {
        //DO NOTHING
    }

    public void accept(DataStoreApiDatabaseServiceVisitor visitor) {
        visitor.visit(this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> get_enum() {
        return _enum;
    }

    public void set_enum(List<String> _enum) {
        this._enum = _enum;
    }

    public String get_default() {
        return _default;
    }

    public void set_default(String _default) {
        this._default = _default;
    }

    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }
}
