# Extensions

## Overview

This package offers utilities an interfaces to extend the parsing of the Data Product Descriptor with
custom extension and specification.

## Features

- Support for custom fields in the Data Product Descriptor specification.
- Support for custom specifications under the `StandardDefinition` `definition` field.

## Project Structure

```
├── ComponentBaseExtendedConverter.java (used to support custom fields in the Data Product Descriptor specification )
├── ComponentBaseExtendedVisitor.java (used to implement visitor pattern on custom fields in the Data Product Descriptor specification )
├── DefinitionConverter.java (used to support custom specifications under the StandardDefinition definition field)
├── DefinitionVisitor.java (used to implement visitor pattern on custom specifications )
├── visitorsimpl/ (contains implementations to handle DefinitionConverters and ComponentBaseExtendedConverters registered on the Parser )
```

## Usage

See the `extensions` test package to have implementation examples.