package org.opendatamesh.dpds.datastoreapi.v1.model;

import org.opendatamesh.dpds.model.core.ComponentBase;

import java.util.Map;

public class DataStoreApi extends ComponentBase {
    private String datastoreapi;
    private DataStoreApiInfo info;
    private Map<String, DataStoreApiDatabaseService> services;
    private DataStoreApiSchema schema;

    public DataStoreApi() {
        //DO NOTHING
    }

    public String getDatastoreapi() {
        return datastoreapi;
    }

    public void setDatastoreapi(String datastoreapi) {
        this.datastoreapi = datastoreapi;
    }

    public DataStoreApiInfo getInfo() {
        return info;
    }

    public void setInfo(DataStoreApiInfo info) {
        this.info = info;
    }

    public Map<String, DataStoreApiDatabaseService> getServices() {
        return services;
    }

    public void setServices(Map<String, DataStoreApiDatabaseService> services) {
        this.services = services;
    }

    public DataStoreApiSchema getSchema() {
        return schema;
    }

    public void setSchema(DataStoreApiSchema schema) {
        this.schema = schema;
    }
}
