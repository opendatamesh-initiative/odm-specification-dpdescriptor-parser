package org.opendatamesh.dpds.visitors.info;

import org.opendatamesh.dpds.model.info.ContactPoint;
import org.opendatamesh.dpds.model.info.Owner;

public interface InfoVisitor {
    void visit(Owner owner);

    void visit(ContactPoint contactPoint);
}
