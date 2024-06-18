package org.opendatamesh.dpds.model.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.opendatamesh.dpds.model.core.SpecificationExtensionPointDPDS;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObligationsDPDS {

    @JsonProperty("termsAndConditions")
    @Schema(description = "Obligations terms and conditions", required = true)
    protected SpecificationExtensionPointDPDS termsAndConditions;

    @JsonProperty("billingPolicy")
    @Schema(description = "Obligations billing policy", required = true)
    protected SpecificationExtensionPointDPDS billingPolicy;
   
    @JsonProperty("sla")
    @Schema(description = "Specification Extension Point of the Obligations", required = true)
    protected SpecificationExtensionPointDPDS sla;
}
