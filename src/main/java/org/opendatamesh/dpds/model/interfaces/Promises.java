package org.opendatamesh.dpds.model.interfaces;

import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.model.core.StandardDefinition;
import org.opendatamesh.dpds.visitors.interfaces.port.PortVisitor;

public class Promises extends ComponentBase {

    private String platform;
    private String servicesType;
    private StandardDefinition api;
    private StandardDefinition deprecationPolicy;
    private StandardDefinition slo;

    public void accept(PortVisitor visitor) {
        visitor.visit(this);
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getServicesType() {
        return servicesType;
    }

    public void setServicesType(String servicesType) {
        this.servicesType = servicesType;
    }

    public StandardDefinition getApi() {
        return api;
    }

    public void setApi(StandardDefinition api) {
        this.api = api;
    }

    public StandardDefinition getDeprecationPolicy() {
        return deprecationPolicy;
    }

    public void setDeprecationPolicy(StandardDefinition deprecationPolicy) {
        this.deprecationPolicy = deprecationPolicy;
    }

    public StandardDefinition getSlo() {
        return slo;
    }

    public void setSlo(StandardDefinition slo) {
        this.slo = slo;
    }
}
