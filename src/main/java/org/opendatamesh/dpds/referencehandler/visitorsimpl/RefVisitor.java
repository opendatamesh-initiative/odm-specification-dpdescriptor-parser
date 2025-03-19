package org.opendatamesh.dpds.referencehandler.visitorsimpl;

import org.opendatamesh.dpds.referencehandler.ReferenceHandler;

public abstract class RefVisitor {
    protected RefVisitor parent;
    protected ReferenceHandler referenceHandler;

    protected RefVisitor(RefVisitor parent) {
        this.parent = parent;
        if (parent != null) {
            this.referenceHandler = parent.referenceHandler;
        }
    }
}
