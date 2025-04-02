package org.opendatamesh.dpds.extensions;

class CustomDefinitionVisitor extends DefinitionVisitor<CustomDefinition> {

    protected CustomDefinitionVisitor() {
        super(CustomDefinition.class);
    }

    @Override
    void visitDefinition(CustomDefinition definition) {
        throw new RuntimeException("OK");
    }

}
