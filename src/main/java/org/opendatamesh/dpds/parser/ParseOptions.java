package org.opendatamesh.dpds.parser;

public class ParseOptions {
    // the base url of ODM Registry API. 
    // Used to rewrite reference to API, Schemas and templates
    private String serverUrl;
    private IdentifierStrategy identifierStrategy = IdentifierStrategyFactory.getDefault();

    private boolean validate = true;
    private boolean validateReadOnlyProperties = false;

    private boolean resolveExternalRef = true;
    private boolean resolveInternalRef = true;

    private boolean resolveReadOnlyProperties = true;

    private boolean rewriteEntityType = true;
    private boolean rewriteFqn = true;
    private boolean rewriteId = true;

    private boolean resolveApiDefinitions = true;
    private boolean resolveTemplateDefinitions = true;

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public IdentifierStrategy getIdentifierStrategy() {
        return identifierStrategy;
    }

    public void setIdentifierStrategy(IdentifierStrategy identifierStrategy) {
        this.identifierStrategy = identifierStrategy;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public boolean isValidateReadOnlyProperties() {
        return validateReadOnlyProperties;
    }

    public void setValidateReadOnlyProperties(boolean validateReadOnlyProperties) {
        this.validateReadOnlyProperties = validateReadOnlyProperties;
    }

    public boolean isResolveExternalRef() {
        return resolveExternalRef;
    }

    public void setResolveExternalRef(boolean resolveExternalRef) {
        this.resolveExternalRef = resolveExternalRef;
    }

    public boolean isResolveInternalRef() {
        return resolveInternalRef;
    }

    public void setResolveInternalRef(boolean resolveInternalRef) {
        this.resolveInternalRef = resolveInternalRef;
    }

    public boolean isResolveReadOnlyProperties() {
        return resolveReadOnlyProperties;
    }

    public void setResolveReadOnlyProperties(boolean resolveReadOnlyProperties) {
        this.resolveReadOnlyProperties = resolveReadOnlyProperties;
    }

    public boolean isRewriteEntityType() {
        return rewriteEntityType;
    }

    public void setRewriteEntityType(boolean rewriteEntityType) {
        this.rewriteEntityType = rewriteEntityType;
    }

    public boolean isRewriteFqn() {
        return rewriteFqn;
    }

    public void setRewriteFqn(boolean rewriteFqn) {
        this.rewriteFqn = rewriteFqn;
    }

    public boolean isRewriteId() {
        return rewriteId;
    }

    public void setRewriteId(boolean rewriteId) {
        this.rewriteId = rewriteId;
    }

    public boolean isResolveApiDefinitions() {
        return resolveApiDefinitions;
    }

    public void setResolveApiDefinitions(boolean resolveApiDefinitions) {
        this.resolveApiDefinitions = resolveApiDefinitions;
    }

    public boolean isResolveTemplateDefinitions() {
        return resolveTemplateDefinitions;
    }

    public void setResolveTemplateDefinitions(boolean resolveTemplateDefinitions) {
        this.resolveTemplateDefinitions = resolveTemplateDefinitions;
    }
}
