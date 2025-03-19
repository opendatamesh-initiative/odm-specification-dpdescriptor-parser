package org.opendatamesh.dpds.model.interfaces;

import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.model.core.StandardDefinition;
import org.opendatamesh.dpds.visitors.interfaces.port.PortVisitor;

public class Obligations extends ComponentBase {

    protected StandardDefinition termsAndConditions;
    protected StandardDefinition billingPolicy;
    protected StandardDefinition sla;

    public void accept(PortVisitor visitor) {
        visitor.visit(this);
    }

    public StandardDefinition getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(StandardDefinition termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public StandardDefinition getBillingPolicy() {
        return billingPolicy;
    }

    public void setBillingPolicy(StandardDefinition billingPolicy) {
        this.billingPolicy = billingPolicy;
    }

    public StandardDefinition getSla() {
        return sla;
    }

    public void setSla(StandardDefinition sla) {
        this.sla = sla;
    }
}
