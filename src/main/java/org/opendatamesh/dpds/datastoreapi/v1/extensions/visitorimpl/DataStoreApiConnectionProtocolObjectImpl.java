package org.opendatamesh.dpds.datastoreapi.v1.extensions.visitorimpl;

import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiJdbcConnectionObject;
import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiOdbcConnectionObject;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiConnectionProtocolObjectVisitor;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiJdbcConnectionObjectVisitor;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiOdbcConnectionObjectVisitor;

public class DataStoreApiConnectionProtocolObjectImpl extends ExtensionVisitor implements DataStoreApiConnectionProtocolObjectVisitor {
    public DataStoreApiConnectionProtocolObjectImpl(ExtensionVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(DataStoreApiJdbcConnectionObject jdbc) {
        extensionHandler.handleComponentBaseExtension(jdbc, DataStoreApiJdbcConnectionObject.class);
        DataStoreApiJdbcConnectionObjectVisitor visitor = new DataStoreApiJdbcConnectionObjectImpl(this);
        if (jdbc.getDriverDocs() != null) {
            jdbc.getDriverDocs().accept(visitor);
        }
    }

    @Override
    public void visit(DataStoreApiOdbcConnectionObject odbc) {
        extensionHandler.handleComponentBaseExtension(odbc, DataStoreApiOdbcConnectionObject.class);
        DataStoreApiOdbcConnectionObjectVisitor visitor = new DataStoreApiOdbcConnectionObjectImpl(this);
        if (odbc.getDriverDocs() != null) {
            odbc.getDriverDocs().accept(visitor);
        }
    }
}
