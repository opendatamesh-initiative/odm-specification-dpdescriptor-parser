package org.opendatamesh.dpds.datastoreapi.v1.model;

import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiVisitor;
import org.opendatamesh.dpds.model.core.ComponentBase;

import java.util.List;

public class DataStoreApiSchema extends ComponentBase {
    private String databaseName;
    private String databaseSchemaName;
    private List<DataStoreApiStandardDefinitionObject> tables;

    public DataStoreApiSchema() {
        //DO NOTHING
    }

    public void accept(DataStoreApiVisitor visitor) {
        visitor.visit(this);
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseSchemaName() {
        return databaseSchemaName;
    }

    public void setDatabaseSchemaName(String databaseSchemaName) {
        this.databaseSchemaName = databaseSchemaName;
    }

    public List<DataStoreApiStandardDefinitionObject> getTables() {
        return tables;
    }

    public void setTables(List<DataStoreApiStandardDefinitionObject> tables) {
        this.tables = tables;
    }
}
