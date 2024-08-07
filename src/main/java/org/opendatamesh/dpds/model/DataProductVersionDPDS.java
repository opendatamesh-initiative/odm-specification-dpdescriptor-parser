package org.opendatamesh.dpds.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.opendatamesh.dpds.model.core.ComponentsDPDS;
import org.opendatamesh.dpds.model.core.ExternalResourceDPDS;
import org.opendatamesh.dpds.model.info.InfoDPDS;
import org.opendatamesh.dpds.model.interfaces.InterfaceComponentsDPDS;
import org.opendatamesh.dpds.model.internals.InternalComponentsDPDS;
import org.opendatamesh.dpds.utils.ObjectMapperFactory;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataProductVersionDPDS implements Cloneable {

    @JsonProperty("dataProductDescriptor")
    @Schema(description = "Data Product Descriptor fully qualified name", required = true)
    private String dataProductDescriptor;

    @JsonProperty("info")
    @Schema(description = "Info object of the Data Product Version", required = true)
    private InfoDPDS info;

    @JsonProperty("interfaceComponents")
    @Schema(description = "Interface Component object of the Data Product Version", required = true)
    private InterfaceComponentsDPDS interfaceComponents;

    @JsonProperty("internalComponents")
    @Schema(description = "Internal Component object of the Data Product Version", required = true)
    private InternalComponentsDPDS internalComponents;

    @JsonProperty("components")
    @Schema(description = "Components object of the Data Product Version", required = true)
    private ComponentsDPDS components;

    @JsonProperty("tags")
    @Schema(description = "List of tags of the Data Product Version", required = true)
    protected List<String> tags = new ArrayList<String>();

    @JsonProperty("externalDocs")
    @Schema(description = "Document of the External Resource of the Data Product Version", required = true)
    private ExternalResourceDPDS externalDocs;

    @JsonProperty("rawContent")
    @Schema(description = "Raw content of the Data Product Version")
    private String rawContent;

    @JsonAnySetter
    public void ignored(String name, Object value) {
    }

    public String toEventString() throws JsonProcessingException {
        return ObjectMapperFactory.JSON_MAPPER.writeValueAsString(this).replace("versionNumber", "version");
    }

    public boolean hasInterfaceComponents() {
        return interfaceComponents != null;    
    }

    public boolean hasInternalComponents() {
        return internalComponents != null;    
    }

     public boolean hasComponents() {
        return components != null;    
    }

    public boolean hasLifecycleInfo() {
        return hasInternalComponents() && internalComponents.hasLifecycleInfo();
    }
}
