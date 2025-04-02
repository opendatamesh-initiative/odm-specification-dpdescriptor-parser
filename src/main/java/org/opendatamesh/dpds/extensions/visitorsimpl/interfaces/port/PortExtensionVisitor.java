package org.opendatamesh.dpds.extensions.visitorsimpl.interfaces.port;

import org.opendatamesh.dpds.extensions.visitorsimpl.ExtensionVisitor;
import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.model.interfaces.Expectations;
import org.opendatamesh.dpds.model.interfaces.Obligations;
import org.opendatamesh.dpds.model.interfaces.Port;
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
        extensionHandler.handleComponentBaseExtension(obligations, Port.class);
        ObligationsVisitor visitor = new ObligationsExtensionVisitor(this);
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
        extensionHandler.handleComponentBaseExtension(expectations, Port.class);
        ExpectationsVisitor visitor = new ExpectationsExtensionVisitor(this);
        if (expectations.getAudience() != null) {
            visitor.visit(expectations.getAudience());
        }
        if (expectations.getUsage() != null) {
            visitor.visit(expectations.getUsage());
        }
    }

    @Override
    public void visit(Promises promises) {
        extensionHandler.handleComponentBaseExtension(promises, Port.class);
        PromisesVisitor visitor = new PromisesExtensionVisitor(this);
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
        extensionHandler.handleComponentBaseExtension(externalDocs, Port.class);
    }
}
