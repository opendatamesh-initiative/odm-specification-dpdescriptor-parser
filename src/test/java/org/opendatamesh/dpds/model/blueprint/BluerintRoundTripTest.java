package org.opendatamesh.dpds.model.blueprint;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.opendatamesh.dpds.model.DataProductVersion;
import org.opendatamesh.dpds.parser.Parser;
import org.opendatamesh.dpds.parser.ParserFactory;

import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class blueprintRoundTripTest {

    @Test
    void deserializeAndSerializeblueprint() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(
                Objects.requireNonNull(
                                        getClass().getResource("data_product_descriptor_with_blueprint.json"),
                                        "classpath resource org/opendatamesh/dpds/model/blueprint/data_product_descriptor_with_blueprint.json"
                )
        );

        Parser parser = ParserFactory.getParser();
        DataProductVersion dpv = parser.deserialize(root);

        assertThat(dpv.getblueprint()).isNotNull();

        JsonNode out = parser.serialize(dpv);
        Map<String, Object> expectedTree = mapper.convertValue(root, new TypeReference<Map<String, Object>>() {});
        Map<String, Object> actualTree = mapper.convertValue(out, new TypeReference<Map<String, Object>>() {});
        assertThat(actualTree)
                .usingRecursiveComparison()
                .isEqualTo(expectedTree);
    }
}
