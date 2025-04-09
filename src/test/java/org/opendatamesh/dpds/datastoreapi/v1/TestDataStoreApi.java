package org.opendatamesh.dpds.datastoreapi.v1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApi;
import org.opendatamesh.dpds.datastoreapi.v1.parser.DataStoreApiParser;
import org.opendatamesh.dpds.datastoreapi.v1.parser.DataStoreApiParserFactory;
import org.opendatamesh.dpds.extensions.DefinitionConverter;
import org.opendatamesh.dpds.extensions.DefinitionVisitor;
import org.opendatamesh.dpds.model.DataProductVersion;
import org.opendatamesh.dpds.model.interfaces.Port;
import org.opendatamesh.dpds.parser.Parser;
import org.opendatamesh.dpds.parser.ParserFactory;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class TestDataStoreApi {
    @Test
    public void testSimpleDataStoreApi() throws IOException {
        JsonNode initialJson = new ObjectMapper().readTree(this.getClass().getResource("data_product_descriptor_simple_data_store_api.json"));

        DataStoreApiParser dataStoreApiParser = DataStoreApiParserFactory.getParser();
        DefinitionConverter<DataStoreApi> dataStoreApiConverter = new DataStoreApiDefinitionConverter(dataStoreApiParser);

        Parser dataProductParser = ParserFactory.getParser()
                .register(dataStoreApiConverter);

        DataProductVersion dataProductVersion = dataProductParser.deserialize(initialJson);
        JsonNode json = dataProductParser.serialize(dataProductVersion);

        assertThat(json)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(initialJson);

        Port outputPort = dataProductVersion.getInterfaceComponents().getOutputPorts()
                .stream()
                .filter(port -> port.getName().equalsIgnoreCase("outputPortName")).findFirst().orElse(null);

        Port outputPort2 = dataProductVersion.getInterfaceComponents().getOutputPorts()
                .stream()
                .filter(port -> port.getName().equalsIgnoreCase("outputPortName2")).findFirst().orElse(null);

        assertThat(outputPort.getPromises().getApi().getDefinition()).isInstanceOf(DataStoreApi.class);
        assertThat(outputPort2.getPromises().getApi().getDefinition()).isNotInstanceOf(DataStoreApi.class);
    }

    @Test
    public void testSimpleDataStoreApiVisitor() throws IOException {
        JsonNode initialJson = new ObjectMapper().readTree(this.getClass().getResource("data_product_descriptor_simple_data_store_api.json"));

        DataStoreApiParser dataStoreApiParser = DataStoreApiParserFactory
                .getParser()
                .register(new DumbDataStoreApiStandardDefinitionObjectConverter());
        DefinitionConverter<DataStoreApi> dataStoreApiConverter = new DataStoreApiDefinitionConverter(dataStoreApiParser);

        Parser dataProductParser = ParserFactory.getParser()
                .register(dataStoreApiConverter);

        DataProductVersion dataProductVersion = dataProductParser.deserialize(initialJson);
        JsonNode json = dataProductParser.serialize(dataProductVersion);

        assertThat(json)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(initialJson);

        AtomicInteger counter = new AtomicInteger(0);
        DefinitionVisitor<DataStoreApi> definitionVisitor = new DataStoreApiDefinitionVisitorImpl(counter);

        dataProductVersion.getInterfaceComponents()
                .getOutputPorts()
                .forEach(port -> definitionVisitor.visit(port.getPromises().getApi().getDefinition()));

        assertThat(counter.get()).isEqualTo(1);
    }
}
