package org.opendatamesh.dpds.datastoreapi.v1.model;

import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiVisitor;
import org.opendatamesh.dpds.model.core.ComponentBase;

import java.util.Map;

public class DataStoreApiDatabaseService extends ComponentBase {
    private String name;
    private String description;
    private DataStoreApiServerInfo serverInfo;
    private Map<String, DatastoreApiVariableObject> variables;

    public DataStoreApiDatabaseService() {
        //DO NOTHING
    }

    public void accept(DataStoreApiVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DataStoreApiServerInfo getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(DataStoreApiServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    public Map<String, DatastoreApiVariableObject> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, DatastoreApiVariableObject> variables) {
        this.variables = variables;
    }
}
