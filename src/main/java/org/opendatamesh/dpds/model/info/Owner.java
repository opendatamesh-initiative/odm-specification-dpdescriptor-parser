package org.opendatamesh.dpds.model.info;

import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.visitors.info.InfoVisitor;

public class Owner extends ComponentBase {

    private String id;
    private String name;

    public void accept(InfoVisitor visitor) {
        visitor.visit(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
