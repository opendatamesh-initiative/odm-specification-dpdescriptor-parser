package org.opendatamesh.dpds.extensions;

import org.opendatamesh.dpds.visitors.core.ComponentBaseVisitor;

class ComponentBaseDumbExtensionVisitorImpl implements ComponentBaseVisitor<ComponentBaseDumbExtension> {

    @Override
    public void visit(ComponentBaseDumbExtension componentBase) {
        throw new RuntimeException("OK");
    }
}
