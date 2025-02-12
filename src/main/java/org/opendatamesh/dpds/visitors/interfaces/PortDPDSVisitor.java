package org.opendatamesh.dpds.visitors.interfaces;

import org.opendatamesh.dpds.model.interfaces.ExpectationsDPDS;
import org.opendatamesh.dpds.model.interfaces.ObligationsDPDS;
import org.opendatamesh.dpds.model.interfaces.PromisesDPDS;
import org.opendatamesh.dpds.visitors.core.ComponentDPDSVisitor;

public interface PortDPDSVisitor extends ComponentDPDSVisitor {
    void visit(ObligationsDPDS obligationsDPDS);

    void visit(ExpectationsDPDS expectationsDPDS);

    void visit(PromisesDPDS promisesDPDS);
}
