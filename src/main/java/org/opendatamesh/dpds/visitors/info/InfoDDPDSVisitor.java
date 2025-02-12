package org.opendatamesh.dpds.visitors.info;

import org.opendatamesh.dpds.model.info.ContactPointDPDS;
import org.opendatamesh.dpds.model.info.OwnerDPDS;

public interface InfoDDPDSVisitor {
    void visit(OwnerDPDS ownerDPDS);

    void visit(ContactPointDPDS contactPointDPDS);
}
