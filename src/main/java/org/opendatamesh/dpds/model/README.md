# Model

## Overview

This package includes the Data Product Descriptor model as specified in the Open Data Mesh specification.

At its core, the `DataProductVersion` acts as the root of the descriptor, while its supporting classes represent
individual components. Each class of the `model` package extends `ComponentBase`. This class contains fields to handle
modular JSON schema combinations (`$ref`, `$def`, etc.).

Additionally, `ComponentBase` provides two key fields:

- `Map<String, JsonNode> additionalProperties`: Stores fields that fall outside the standard ODM specification and
  cannot be mapped to the primary model.
- `Map<String, ComponentBase> customProperties`: Holds fields that are not covered by the standard ODM specification but
  can be
  processed using the `DefinitionConverter` and `ComponentBaseExtendedConverter` implementations registered in the
  `Parser`.

