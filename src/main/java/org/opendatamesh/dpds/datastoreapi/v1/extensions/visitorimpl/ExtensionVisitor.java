package org.opendatamesh.dpds.datastoreapi.v1.extensions.visitorimpl;

public abstract class ExtensionVisitor {
    protected ExtensionVisitor parent;
    protected ExtensionHandler extensionHandler;

    protected ExtensionVisitor(ExtensionVisitor parent) {
        this.parent = parent;
        if (parent != null) {
            this.extensionHandler = parent.extensionHandler;
        }
    }
}
