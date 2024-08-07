package org.opendatamesh.dpds.old.utils;

import org.opendatamesh.dpds.model.DataProductVersionDPDS;


public interface ResourceObjectChecker {
    public void verifyAll(DataProductVersionDPDS descriptor);
    public  void verifyCoreInfo(DataProductVersionDPDS descriptor);
    public void verifyCoreInterfaces(DataProductVersionDPDS descriptor) ;
    public void verifyCoreApplicationComponents(DataProductVersionDPDS descriptor);
    public void verifyCoreInfrastructuralComponents(DataProductVersionDPDS descriptor);
    public void verifyLifecycle(DataProductVersionDPDS descriptor);
}
