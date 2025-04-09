package org.opendatamesh.dpds.datastoreapi.v1.extensions.visitorimpl;

import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiServerInfo;
import org.opendatamesh.dpds.datastoreapi.v1.model.DatastoreApiVariableObject;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiDatabaseServiceVisitor;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiServerInfoVisitor;

public class DataStoreApiDatabaseServiceVisitorImpl extends ExtensionVisitor implements DataStoreApiDatabaseServiceVisitor {
    public DataStoreApiDatabaseServiceVisitorImpl(ExtensionVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(DataStoreApiServerInfo serverInfo) {
        extensionHandler.handleComponentBaseExtension(serverInfo, DataStoreApiServerInfo.class);
        DataStoreApiServerInfoVisitor visitor = new DataStoreApiServerInfoVisitorImpl(this);
        if (serverInfo.getConnectionProtocols() != null) {
            visitor.visit(serverInfo.getConnectionProtocols());
        }
    }

    @Override
    public void visit(DatastoreApiVariableObject variableObject) {
        extensionHandler.handleComponentBaseExtension(variableObject, DatastoreApiVariableObject.class);
    }
}
