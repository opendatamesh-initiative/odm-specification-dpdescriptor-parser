package org.opendatamesh.dpds.visitors.interfaces.port;

import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.model.interfaces.Expectations;
import org.opendatamesh.dpds.model.interfaces.Obligations;
import org.opendatamesh.dpds.model.interfaces.Promises;

public interface PortVisitor {
    void visit(Obligations obligations);

    void visit(Expectations expectations);

    void visit(Promises promises);

    void visit(ExternalDocs externalDocs);
}
