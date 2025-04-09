package org.opendatamesh.dpds.datastoreapi.v1.model;

import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiDatabaseServiceVisitor;
import org.opendatamesh.dpds.model.core.ComponentBase;

public class DataStoreApiServerInfo extends ComponentBase {
    private String host;
    private String port;
    private String dbmsType;
    private String dbmsVersion;
    private DataStoreApiConnectionProtocolObject connectionProtocols;

    public DataStoreApiServerInfo() {
        //DO NOTHING
    }

    public void accept(DataStoreApiDatabaseServiceVisitor visitor) {
        visitor.visit(this);
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDbmsType() {
        return dbmsType;
    }

    public void setDbmsType(String dbmsType) {
        this.dbmsType = dbmsType;
    }

    public String getDbmsVersion() {
        return dbmsVersion;
    }

    public void setDbmsVersion(String dbmsVersion) {
        this.dbmsVersion = dbmsVersion;
    }

    public DataStoreApiConnectionProtocolObject getConnectionProtocols() {
        return connectionProtocols;
    }

    public void setConnectionProtocols(DataStoreApiConnectionProtocolObject connectionProtocols) {
        this.connectionProtocols = connectionProtocols;
    }
}
