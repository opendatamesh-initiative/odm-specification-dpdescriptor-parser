package org.opendatamesh.dpds.datastoreapi.v1.model;

import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiInfoVisitor;
import org.opendatamesh.dpds.model.core.ComponentBase;

public class DataStoreApiLicense extends ComponentBase {
    private String name;
    private String url;

    public DataStoreApiLicense() {
        //DO NOTHING
    }

    public void accept(DataStoreApiInfoVisitor visitor) {
        visitor.visit(this);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
