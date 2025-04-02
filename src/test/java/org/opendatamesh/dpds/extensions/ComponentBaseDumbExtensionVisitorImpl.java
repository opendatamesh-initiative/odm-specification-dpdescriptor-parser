package org.opendatamesh.dpds.extensions;

class ComponentBaseDumbExtensionVisitorImpl extends ComponentBaseExtendedVisitor<ComponentBaseDumbExtension> {

    public ComponentBaseDumbExtensionVisitorImpl() {
        super(ComponentBaseDumbExtension.class);
    }

    @Override
    void visitExtension(ComponentBaseDumbExtension componentBase) {
        throw new RuntimeException("OK");
    }
}
