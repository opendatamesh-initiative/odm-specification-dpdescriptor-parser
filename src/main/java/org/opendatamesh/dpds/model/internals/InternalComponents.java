package org.opendatamesh.dpds.model.internals;

import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.visitors.DataProductVersionVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InternalComponents extends ComponentBase {

    private  Map<String, List<LifecycleTaskInfo>> lifecycleInfo;
    private List<ApplicationComponent> applicationComponents = new ArrayList<>();
    private List<InfrastructuralComponent> infrastructuralComponents = new ArrayList<>();

    public void accept(DataProductVersionVisitor visitor) {
        visitor.visit(this);
    }

    public Map<String, List<LifecycleTaskInfo>> getLifecycleInfo() {
        return lifecycleInfo;
    }

    public void setLifecycleInfo( Map<String, List<LifecycleTaskInfo>> lifecycleInfo) {
        this.lifecycleInfo = lifecycleInfo;
    }

    public List<ApplicationComponent> getApplicationComponents() {
        return applicationComponents;
    }

    public void setApplicationComponents(List<ApplicationComponent> applicationComponents) {
        this.applicationComponents = applicationComponents;
    }

    public List<InfrastructuralComponent> getInfrastructuralComponents() {
        return infrastructuralComponents;
    }

    public void setInfrastructuralComponents(List<InfrastructuralComponent> infrastructuralComponents) {
        this.infrastructuralComponents = infrastructuralComponents;
    }
}
