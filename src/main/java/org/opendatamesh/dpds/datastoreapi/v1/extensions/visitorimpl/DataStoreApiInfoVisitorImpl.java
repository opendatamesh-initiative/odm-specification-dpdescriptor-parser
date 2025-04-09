package org.opendatamesh.dpds.datastoreapi.v1.extensions.visitorimpl;

import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiContact;
import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiLicense;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiInfoVisitor;

public class DataStoreApiInfoVisitorImpl extends ExtensionVisitor implements DataStoreApiInfoVisitor {

    public DataStoreApiInfoVisitorImpl(ExtensionVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(DataStoreApiContact contact) {
        extensionHandler.handleComponentBaseExtension(contact, DataStoreApiContact.class);
    }

    @Override
    public void visit(DataStoreApiLicense license) {
        extensionHandler.handleComponentBaseExtension(license, DataStoreApiLicense.class);
    }
}
