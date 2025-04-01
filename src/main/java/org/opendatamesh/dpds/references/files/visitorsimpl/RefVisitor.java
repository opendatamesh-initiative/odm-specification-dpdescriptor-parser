package org.opendatamesh.dpds.references.files.visitorsimpl;

import org.opendatamesh.dpds.references.files.ReferenceFileHandler;

public abstract class RefVisitor {
    protected RefVisitor parent;
    protected ReferenceFileHandler referenceFileHandler;

    protected RefVisitor(RefVisitor parent) {
        this.parent = parent;
        if (parent != null) {
            this.referenceFileHandler = parent.referenceFileHandler;
        }
    }
}
