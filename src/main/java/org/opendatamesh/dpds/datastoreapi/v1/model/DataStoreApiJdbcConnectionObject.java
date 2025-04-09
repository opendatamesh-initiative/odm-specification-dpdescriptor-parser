package org.opendatamesh.dpds.datastoreapi.v1.model;

import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiConnectionProtocolObjectVisitor;
import org.opendatamesh.dpds.model.core.ComponentBase;

public class DataStoreApiJdbcConnectionObject extends ComponentBase {
    private String version;
    private String connectionString;
    private String driverName;
    private String driverClass;
    private String driverVersion;
    private DataStoreApiExternalResourceObject driverLibrary;
    private DataStoreApiExternalResourceObject driverDocs;

    public DataStoreApiJdbcConnectionObject() {
        //DO NOTHING
    }

    public void accept(DataStoreApiConnectionProtocolObjectVisitor visitor) {
        visitor.visit(this);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getDriverVersion() {
        return driverVersion;
    }

    public void setDriverVersion(String driverVersion) {
        this.driverVersion = driverVersion;
    }

    public DataStoreApiExternalResourceObject getDriverLibrary() {
        return driverLibrary;
    }

    public void setDriverLibrary(DataStoreApiExternalResourceObject driverLibrary) {
        this.driverLibrary = driverLibrary;
    }

    public DataStoreApiExternalResourceObject getDriverDocs() {
        return driverDocs;
    }

    public void setDriverDocs(DataStoreApiExternalResourceObject driverDocs) {
        this.driverDocs = driverDocs;
    }
}
