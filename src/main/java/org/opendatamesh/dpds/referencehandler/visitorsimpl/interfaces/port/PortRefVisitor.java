package org.opendatamesh.dpds.referencehandler.visitorsimpl.interfaces.port;

import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.model.interfaces.Expectations;
import org.opendatamesh.dpds.model.interfaces.Obligations;
import org.opendatamesh.dpds.model.interfaces.Promises;
import org.opendatamesh.dpds.referencehandler.visitorsimpl.RefVisitor;
import org.opendatamesh.dpds.visitors.interfaces.port.ExpectationsVisitor;
import org.opendatamesh.dpds.visitors.interfaces.port.ObligationsVisitor;
import org.opendatamesh.dpds.visitors.interfaces.port.PortVisitor;
import org.opendatamesh.dpds.visitors.interfaces.port.PromisesVisitor;

public class PortRefVisitor extends RefVisitor implements PortVisitor {
    public PortRefVisitor(RefVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(Obligations obligations) {
        referenceHandler.handleComponentBaseReference(obligations);
        ObligationsVisitor visitor = new ObligationsRefVisitor(this);
        if (obligations.getTermsAndConditions() != null) {
            visitor.visit(obligations.getTermsAndConditions());
        }
        if (obligations.getBillingPolicy() != null) {
            visitor.visit(obligations.getBillingPolicy());
        }
        if (obligations.getSla() != null) {
            visitor.visit(obligations.getSla());
        }
    }

    @Override
    public void visit(Expectations expectations) {
        referenceHandler.handleComponentBaseReference(expectations);
        ExpectationsVisitor visitor = new ExpectationsRefVisitor(this);
        if (expectations.getAudience() != null) {
            visitor.visit(expectations.getAudience());
        }
        if (expectations.getUsage() != null) {
            visitor.visit(expectations.getUsage());
        }
    }

    @Override
    public void visit(Promises promises) {
        referenceHandler.handleComponentBaseReference(promises);
        PromisesVisitor visitor = new PromisesRefVisitor(this);
        if (promises.getApi() != null) {
            visitor.visit(promises.getApi());
        }
        if (promises.getDeprecationPolicy() != null) {
            visitor.visit(promises.getDeprecationPolicy());
        }
        if (promises.getSlo() != null) {
            visitor.visit(promises.getSlo());
        }
    }

    @Override
    public void visit(ExternalDocs externalDocs) {
        referenceHandler.handleComponentBaseReference(externalDocs);
    }
}
