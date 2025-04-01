# Parser

## Overview

Package to facilitate the serialization and deserialization of JSON data structures into Java objects, specifically
handling Data Product Descriptor files. Users can register custom converters for extending and
modifying the default parsing behavior.

## Usage

```java
JsonNode initialJson /* = [...] */; //Initial JsonNode object loaded from the Data Product Descriptor file.
Parser parser = ParserFactory.getParser() //Use getParser(ObjectMapper mapper) to pass a pre-configured ObjectMapper
        .register(new ComponentBaseDumbExtensionConverter()) //Register any implementation of ComponentBaseExtendedConverter
        //[...]
        .register(new CustomDefinitionConverter()); //Register any implementation of DefinitionConverter

//Usage example
DataProductVersion pojo = parser.deserialize(initialJson);
JsonNode rawJson = parser.serialize(pojo);
```