package org.opendatamesh.dpds.parser;

import lombok.Data;

@Data
public class ParseOptions {
    // the base url of ODM Registry API. 
    // Used to rewrite reference to API, Schemas and templates
    private  String serverUrl;
    IdentifierStrategy identifierStrategy = IdentifierStrategy.DEFUALT;

    private boolean validate = true;
    private boolean validateReadOnlyProperties = false;

    private boolean resoveExternalRef = true;
    private boolean resoveInternalRef = true;
    
    private boolean resoveReadOnlyProperties = true;
    
    private boolean rewriteEntityType = true;
    private boolean rewriteFqn = true;
    private boolean rewriteId = true;

    private boolean resoveApiDefinitions = true;
    private boolean resoveTemplateDefinitions = true;
}
