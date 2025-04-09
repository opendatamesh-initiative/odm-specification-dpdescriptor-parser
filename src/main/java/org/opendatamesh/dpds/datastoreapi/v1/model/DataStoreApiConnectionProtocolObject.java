package org.opendatamesh.dpds.datastoreapi.v1.model;

import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiServerInfoVisitor;
import org.opendatamesh.dpds.model.core.ComponentBase;

public class DataStoreApiConnectionProtocolObject extends ComponentBase {
    private DataStoreApiJdbcConnectionObject jdbc;
    private DataStoreApiOdbcConnectionObject odbc;

    public DataStoreApiConnectionProtocolObject() {
        //DO NOTHING
    }

    public void accept(DataStoreApiServerInfoVisitor visitor) {
        visitor.visit(this);
    }

    public DataStoreApiJdbcConnectionObject getJdbc() {
        return jdbc;
    }

    public void setJdbc(DataStoreApiJdbcConnectionObject jdbc) {
        this.jdbc = jdbc;
    }

    public DataStoreApiOdbcConnectionObject getOdbc() {
        return odbc;
    }

    public void setOdbc(DataStoreApiOdbcConnectionObject odbc) {
        this.odbc = odbc;
    }
}
