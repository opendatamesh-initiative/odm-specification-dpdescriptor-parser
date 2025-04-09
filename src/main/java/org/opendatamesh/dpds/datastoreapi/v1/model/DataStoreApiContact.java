package org.opendatamesh.dpds.datastoreapi.v1.model;

import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiInfoVisitor;
import org.opendatamesh.dpds.model.core.ComponentBase;

public class DataStoreApiContact extends ComponentBase {
    private String name;
    private String url;
    private String email;

    public DataStoreApiContact() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
