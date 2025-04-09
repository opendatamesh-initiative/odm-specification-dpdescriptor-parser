package org.opendatamesh.dpds.datastoreapi.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiJdbcConnectionObjectVisitor;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiOdbcConnectionObjectVisitor;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiStandardDefinitionObjectVisitor;
import org.opendatamesh.dpds.model.core.ComponentBase;

public class DataStoreApiExternalResourceObject extends ComponentBase {
    private String description;
    private String mediaType;
    @JsonProperty("$href")
    private String href;

    public DataStoreApiExternalResourceObject() {
        //DO NOTHING
    }

    public void accept(DataStoreApiJdbcConnectionObjectVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(DataStoreApiOdbcConnectionObjectVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(DataStoreApiStandardDefinitionObjectVisitor visitor) {
        visitor.visit(this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getMediaType() {
        return mediaType;
    }

    @Override
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
