package org.opendatamesh.dpds.extensions;

import org.opendatamesh.dpds.model.core.ComponentBase;

class ComponentBaseDumbExtension extends ComponentBase {
    private String fieldA;
    private String fieldB;

    public ComponentBaseDumbExtension() {
        //Empty
    }

    public String getFieldA() {
        return fieldA;
    }

    public void setFieldA(String fieldA) {
        this.fieldA = fieldA;
    }

    public String getFieldB() {
        return fieldB;
    }

    public void setFieldB(String fieldB) {
        this.fieldB = fieldB;
    }
}
