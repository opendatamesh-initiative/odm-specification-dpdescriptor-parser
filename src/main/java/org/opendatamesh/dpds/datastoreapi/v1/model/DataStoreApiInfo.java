package org.opendatamesh.dpds.datastoreapi.v1.model;

import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiVisitor;
import org.opendatamesh.dpds.model.core.ComponentBase;

public class DataStoreApiInfo extends ComponentBase {
    private String title;
    private String summary;
    private String description;
    private String termsOfService;
    private String version;
    private String datastoreName;
    private DataStoreApiContact contact;
    private DataStoreApiLicense license;

    public DataStoreApiInfo() {
        //DO NOTHING
    }

    public void accept(DataStoreApiVisitor visitor) {
        visitor.visit(this);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTermsOfService() {
        return termsOfService;
    }

    public void setTermsOfService(String termsOfService) {
        this.termsOfService = termsOfService;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDatastoreName() {
        return datastoreName;
    }

    public void setDatastoreName(String datastoreName) {
        this.datastoreName = datastoreName;
    }

    public DataStoreApiContact getContact() {
        return contact;
    }

    public void setContact(DataStoreApiContact contact) {
        this.contact = contact;
    }

    public DataStoreApiLicense getLicense() {
        return license;
    }

    public void setLicense(DataStoreApiLicense license) {
        this.license = license;
    }
}
