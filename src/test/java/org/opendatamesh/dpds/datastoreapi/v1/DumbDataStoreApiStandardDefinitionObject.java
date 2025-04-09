package org.opendatamesh.dpds.datastoreapi.v1;

import org.opendatamesh.dpds.model.core.ComponentBase;

class DumbDataStoreApiStandardDefinitionObject extends ComponentBase {
    private String specField;

    DumbDataStoreApiStandardDefinitionObject() {
    }

    public String getSpecField() {
        return specField;
    }

    public void setSpecField(String specField) {
        this.specField = specField;
    }
}
