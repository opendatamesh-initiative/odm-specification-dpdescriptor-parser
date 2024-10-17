package org.opendatamesh.dpds.model.internals;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.opendatamesh.dpds.model.core.ExternalResourceDPDS;
import org.opendatamesh.dpds.model.core.StandardDefinitionDPDS;

import java.util.Map;

@Data
public class LifecycleTaskInfoDPDS {

    @JsonProperty("name")
    @Schema(description = "Lifecycle Task Info name", required = true)
    private String name;

    @JsonProperty("stageName")
    @Schema(description = "Name of the goal stage of the task", required = true)
    private String stageName;

    @JsonProperty("order")
    @Schema(description = "Order of the task", required = true)
    private Integer order;

    @JsonProperty("service")
    @Schema(description = "External Resource object of the service of the task", required = true)
    private ExternalResourceDPDS service;

    @JsonProperty("template")
    @Schema(description = "Standard Definition object of the template of the task", required = true)
    private StandardDefinitionDPDS template;

    @JsonProperty("configurations")
    @Schema(description = "Key-value list of configrations of the Task", required = true)
    private Map<String, Object> configurations;

    @Schema(description = "Raw Content of the task")
    @JsonProperty("rawContent")
    String rawContent;

    public boolean hasTemplate() {
        return template != null;
    }

    public boolean hasConfigurations() {
        return configurations != null;
    }
}
