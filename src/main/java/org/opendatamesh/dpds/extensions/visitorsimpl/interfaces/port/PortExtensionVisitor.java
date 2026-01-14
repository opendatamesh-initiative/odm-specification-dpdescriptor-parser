package org.opendatamesh.dpds.extensions.visitorsimpl.interfaces.port;

import org.opendatamesh.dpds.extensions.visitorsimpl.ExtensionVisitor;
import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.model.interfaces.Expectations;
import org.opendatamesh.dpds.model.interfaces.Obligations;
import org.opendatamesh.dpds.model.interfaces.Promises;
import org.opendatamesh.dpds.visitors.interfaces.port.ExpectationsVisitor;
import org.opendatamesh.dpds.visitors.interfaces.port.ObligationsVisitor;
import org.opendatamesh.dpds.visitors.interfaces.port.PortVisitor;
import org.opendatamesh.dpds.visitors.interfaces.port.PromisesVisitor;

public class PortExtensionVisitor extends ExtensionVisitor implements PortVisitor {
    public PortExtensionVisitor(ExtensionVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(Obligations obligations) {
        extensionHandler.handleComponentBaseExtension(obligations, Obligations.class);
        ObligationsVisitor visitor = new ObligationsExtensionVisitor(this);
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
        extensionHandler.handleComponentBaseExtension(expectations, Expectations.class);
        ExpectationsVisitor visitor = new ExpectationsExtensionVisitor(this);
        if (expectations.getAudience() != null) {
            expectations.getAudience().accept(visitor);
        }
        if (expectations.getUsage() != null) {
            expectations.getUsage().accept(visitor);
        }
    }

    @Override
    public void visit(Promises promises) {
        extensionHandler.handleComponentBaseExtension(promises, Promises.class);
        PromisesVisitor visitor = new PromisesExtensionVisitor(this);
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
        extensionHandler.handleComponentBaseExtension(externalDocs, ExternalDocs.class);
    }
}
