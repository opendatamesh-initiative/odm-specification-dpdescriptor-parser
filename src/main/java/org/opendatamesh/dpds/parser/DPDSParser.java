package org.opendatamesh.dpds.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.networknt.schema.ValidationMessage;
import lombok.Data;
import org.opendatamesh.dpds.exceptions.*;
import org.opendatamesh.dpds.location.DescriptorLocation;
import org.opendatamesh.dpds.model.DataProductVersionDPDS;
import org.opendatamesh.dpds.processors.ProcessorType;
import org.opendatamesh.dpds.processors.PropertiesProcessorFactory;
import org.opendatamesh.dpds.utils.ObjectMapperFactory;

import java.util.Set;

@Data
public class DPDSParser {

    private String validationSchemaBaseUrl;

    private DataProductVersionValidator schemaValidator;

    public DPDSParser(
            String validationSchemaBaseUrl,
            String validationSchemaMinSupportedVersion,
            String validationSchemaMaxSupportedVersion
    ) {
        this.validationSchemaBaseUrl = validationSchemaBaseUrl;
        this.schemaValidator = new DataProductVersionValidator(
                validationSchemaBaseUrl,
                validationSchemaMinSupportedVersion,
                validationSchemaMaxSupportedVersion
        );
    }

    public ParseResult parse(DescriptorLocation location, ParseOptions options) throws ParseException {
        try {
            location.open();
        } catch (FetchException e) {
            throw new ParseException("Impossible to open location", ParseException.Stage.LOAD_ROOT_DOC, e);
        }

        ParseContext context = new ParseContext(location, options);
        parseRootDoc(context);

        if (options.isResolveExternalRef())
            processExternalReferences(context);

        if (options.isResolveApiDefinitions()) {
            processApiDefinitions(context);
        }

        if (options.isResolveReadOnlyProperties())
            processReadOnlyProperties(context);

        if (context.getOptions().isValidate()) {
            try {
                Set<ValidationMessage> errors = validateSchema(context.getResult().getDescriptorDocument());
                if (!errors.isEmpty()) {
                    throw new ValidationException(
                            "Descriptor document does not comply with DPDS. The following validation errors has been found during validation ["
                                    + errors + "]",
                            errors);
                }
            } catch (DeserializationException | ValidationException e) {
                throw new ParseException("Parsed document is invalid",
                        ParseException.Stage.VALIDATE, e);
            }
        }

        try {
            location.close();
        } catch (FetchException e) {
            throw new ParseException("Impossible to close location", ParseException.Stage.LOAD_ROOT_DOC, e);
        }

        return context.getResult();
    }

    public Set<ValidationMessage> validateSchema(
            DataProductVersionDPDS descriptor
    ) throws DeserializationException {
        try {
            DPDSSerializer serializer = new DPDSSerializer("json", false);
            String serializedContent = serializer.serialize(descriptor, "canonical");
            return schemaValidator.validateSchema(serializedContent, descriptor.getDataProductDescriptor());
        } catch (JsonProcessingException e) {
            throw new DeserializationException("Impossible to serialize data product version raw content", e);
        }
    }

    private DPDSParser parseRootDoc(ParseContext context) throws ParseException {
        try {
            DataProductVersionDPDS descriptorDocument = null;
            String rawContent = context.getLocation().fetchRootDoc();
            context.setMapper(ObjectMapperFactory.getRightMapper(rawContent));

            DPDSDeserializer deserializer = new DPDSDeserializer();
            descriptorDocument = deserializer.deserialize(rawContent);
            context.getResult().setDescriptorDocument(descriptorDocument);
        } catch (FetchException | DeserializationException e) {
            throw new ParseException("Impossible to parse root descriptor document",
                    ParseException.Stage.LOAD_ROOT_DOC, e);
        }

        return this;
    }

    private DPDSParser processExternalReferences(ParseContext context) throws ParseException {
        try {
            PropertiesProcessorFactory.getPropertyProcessor(ProcessorType.REFERENCES, context).process();
        } catch (UnresolvableReferenceException | DeserializationException | JsonProcessingException e) {
            throw new ParseException("Impossible to resolve external reference of root descriptor document",
                    ParseException.Stage.RESOLVE_EXTERNAL_REFERENCES, e);
        }
        return this;
    }

    private DPDSParser processReadOnlyProperties(ParseContext context) throws ParseException {

        try {
            PropertiesProcessorFactory.getPropertyProcessor(ProcessorType.READ_ONLY, context).process();
        } catch (UnresolvableReferenceException | DeserializationException | JsonProcessingException e) {
            throw new ParseException("Impossible to process read only properties",
                    ParseException.Stage.RESOLVE_READ_ONLY_PROPERTIES, e);
        }

        return this;
    }

    private DPDSParser processApiDefinitions(ParseContext context) throws ParseException {
        try {
            PropertiesProcessorFactory.getPropertyProcessor(ProcessorType.API_DEFINITION, context).process();
        } catch (UnresolvableReferenceException | DeserializationException | JsonProcessingException e) {
            throw new ParseException("Impossible to process Api definitions",
                    ParseException.Stage.RESOLVE_STANDARD_DEFINITIONS, e);
        }

        return this;
    }
}
