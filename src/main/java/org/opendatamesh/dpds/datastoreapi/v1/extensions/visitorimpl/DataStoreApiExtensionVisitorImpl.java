package org.opendatamesh.dpds.datastoreapi.v1.extensions.visitorimpl;

import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiDatabaseService;
import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiInfo;
import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiSchema;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiDatabaseServiceVisitor;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiInfoVisitor;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiSchemaVisitor;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiVisitor;

public class DataStoreApiExtensionVisitorImpl extends ExtensionVisitor implements DataStoreApiVisitor {

    public DataStoreApiExtensionVisitorImpl(ExtensionHandler extensionHandler) {
        super(null);
        this.extensionHandler = extensionHandler;
    }


    @Override
    public void visit(DataStoreApiInfo info) {
        extensionHandler.handleComponentBaseExtension(info, DataStoreApiInfo.class);
        DataStoreApiInfoVisitor visitor = new DataStoreApiInfoVisitorImpl(this);
        if (info.getContact() != null) {
            visitor.visit(info.getContact());
        }
        if (info.getLicense() != null) {
            visitor.visit(info.getLicense());
        }
    }

    @Override
    public void visit(DataStoreApiDatabaseService databaseService) {
        extensionHandler.handleComponentBaseExtension(databaseService, DataStoreApiDatabaseService.class);
        DataStoreApiDatabaseServiceVisitor visitor = new DataStoreApiDatabaseServiceVisitorImpl(this);
        if (databaseService.getServerInfo() != null) {
            visitor.visit(databaseService.getServerInfo());
        }
        if (databaseService.getVariables() != null) {
            databaseService.getVariables().forEach((key, value) -> visitor.visit(value));
        }
    }

    @Override
    public void visit(DataStoreApiSchema schema) {
        extensionHandler.handleComponentBaseExtension(schema, DataStoreApiSchema.class);
        DataStoreApiSchemaVisitor visitor = new DataStoreApiSchemaVisitorImpl(this);
        if (schema.getTables() != null) {
            schema.getTables().forEach(visitor::visit);
        }
    }
}
