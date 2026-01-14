package org.opendatamesh.dpds.references.files.visitorsimpl.interfaces.port;

import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.model.interfaces.Expectations;
import org.opendatamesh.dpds.model.interfaces.Obligations;
import org.opendatamesh.dpds.model.interfaces.Promises;
import org.opendatamesh.dpds.references.files.visitorsimpl.RefVisitor;
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
        referenceFileHandler.handleComponentBaseReference(obligations);
        ObligationsVisitor visitor = new ObligationsRefVisitor(this);
        if (obligations.getTermsAndConditions() != null) {
            obligations.getTermsAndConditions().accept(visitor);
        }
        if (obligations.getBillingPolicy() != null) {
            obligations.getBillingPolicy().accept(visitor);
        }
        if (obligations.getSla() != null) {
            obligations.getSla().accept(visitor);
        }
    }

    @Override
    public void visit(Expectations expectations) {
        referenceFileHandler.handleComponentBaseReference(expectations);
        ExpectationsVisitor visitor = new ExpectationsRefVisitor(this);
        if (expectations.getAudience() != null) {
            expectations.getAudience().accept(visitor);
        }
        if (expectations.getUsage() != null) {
            expectations.getUsage().accept(visitor);
        }
    }

    @Override
    public void visit(Promises promises) {
        referenceFileHandler.handleComponentBaseReference(promises);
        PromisesVisitor visitor = new PromisesRefVisitor(this);
        if (promises.getApi() != null) {
            promises.getApi().accept(visitor);
        }
        if (promises.getDeprecationPolicy() != null) {
            promises.getDeprecationPolicy().accept(visitor);
        }
        if (promises.getSlo() != null) {
            promises.getSlo().accept(visitor);
        }
    }

    @Override
    public void visit(ExternalDocs externalDocs) {
        referenceFileHandler.handleComponentBaseReference(externalDocs);
    }
}
