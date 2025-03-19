package org.opendatamesh.dpds.model;

import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.model.components.Components;
import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.model.info.Info;
import org.opendatamesh.dpds.model.interfaces.InterfaceComponents;
import org.opendatamesh.dpds.model.internals.InternalComponents;

import java.util.ArrayList;
import java.util.List;

public class DataProductVersion extends ComponentBase {

    private String dataProductDescriptor;
    private Info info;
    private InterfaceComponents interfaceComponents;
    private InternalComponents internalComponents;
    private Components components;
    private List<String> tags = new ArrayList<>();
    private ExternalDocs externalDocs;

    public String getDataProductDescriptor() {
        return dataProductDescriptor;
    }

    public void setDataProductDescriptor(String dataProductDescriptor) {
        this.dataProductDescriptor = dataProductDescriptor;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public InterfaceComponents getInterfaceComponents() {
        return interfaceComponents;
    }

    public void setInterfaceComponents(InterfaceComponents interfaceComponents) {
        this.interfaceComponents = interfaceComponents;
    }

    public InternalComponents getInternalComponents() {
        return internalComponents;
    }

    public void setInternalComponents(InternalComponents internalComponents) {
        this.internalComponents = internalComponents;
    }

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public ExternalDocs getExternalDocs() {
        return externalDocs;
    }

    public void setExternalDocs(ExternalDocs externalDocs) {
        this.externalDocs = externalDocs;
    }
}
