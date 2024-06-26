package org.opendatamesh.dpds.model.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.opendatamesh.dpds.model.core.ComponentDPDS;


@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)
public class PortDPDS extends ComponentDPDS {

    @JsonProperty("promises")
    @Schema(description = "Promises object of the Port")
    protected PromisesDPDS promises;

    @JsonProperty("expectations")
    @Schema(description = "Expectations object of the Port")
    protected ExpectationsDPDS expectations;

    @JsonProperty("obligations")
    @Schema(description = "Obligations object of the Port")
    protected ObligationsDPDS obligations;

    public boolean hasPromises() {
        return promises != null;
    }

    public boolean hasApi() {
        return hasPromises() && promises.getApi() != null;
    }

    public boolean hasApiDefinition() {
        return hasApi() && promises.getApi().getDefinition() != null;
    }

}

