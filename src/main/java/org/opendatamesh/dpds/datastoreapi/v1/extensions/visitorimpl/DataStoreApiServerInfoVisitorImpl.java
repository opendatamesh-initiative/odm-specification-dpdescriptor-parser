package org.opendatamesh.dpds.datastoreapi.v1.extensions.visitorimpl;

import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiConnectionProtocolObject;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiConnectionProtocolObjectVisitor;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiServerInfoVisitor;

public class DataStoreApiServerInfoVisitorImpl extends ExtensionVisitor implements DataStoreApiServerInfoVisitor {
    public DataStoreApiServerInfoVisitorImpl(ExtensionVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(DataStoreApiConnectionProtocolObject connectionProtocolObject) {
        extensionHandler.handleComponentBaseExtension(connectionProtocolObject, DataStoreApiConnectionProtocolObject.class);
        DataStoreApiConnectionProtocolObjectVisitor visitor = new DataStoreApiConnectionProtocolObjectImpl(this);
        if(connectionProtocolObject.getJdbc() != null){
            connectionProtocolObject.getJdbc().accept(visitor);
        }
        if(connectionProtocolObject.getOdbc() != null){
            connectionProtocolObject.getOdbc().accept(visitor);
        }
    }
}
