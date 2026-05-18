<a name="readme-top"></a>

<!-- PROJECT LOGO -->
<br />
<div align="center">
    <img src="https://dpds.opendatamesh.org/images/logos/opendatamesh.png" alt="Logo" width="80" height="80">

<h3 align="center">odm-specification-dpdescriptor-parser</h3>

  <p align="center">
  Java utilities for reading and writing Open Data Mesh's Data Product Descriptor Specification.
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#overview">Overview</a>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#blueprint-lineage">Blueprint lineage (<code>blueprint</code>)</a></li>
    <li><a href="#examples">Examples</a></li>
    <li><a href="#prerequisites">Prerequisites</a></li>
    <li><a href="#installation">Installation</a></li>
    <li><a href="#releases">Releases</a></li>
  </ol>
</details>

<!-- Overview -->

## Overview

This library is used for reading, writing, and validating Data Product Descriptors as defined by the Open Data Mesh
initiative. It provides tools to deserialize data descriptors into Java objects, validate their schemas, and manage
descriptor locations, supporting URI and GIT navigation. The library is structured into several packages, each with a
specific focus, such as model, parser, location, exceptions, and API.

<!-- USAGE EXAMPLES -->

## Usage

#### Model

The `/model` package contains the structure of the Data Product Descriptor. The root object is `DataProductVersionDPDS`.

#### Parser

The `/parser` package contains all the code that is used to deserialize a Data Product Descriptor and map it into
a `DataProductVersionDPDS` object. Inside the package, the `DPDSParser` can be used to:

- `parse`: Given a `DescriptorLocation` and `ParseOptions`, this method returns a `DataProductVersionDPDS`, which can be
  easily navigated.
- `validateSchema`: Given a data product descriptor, it checks that the schema is valid.

#### Location

The `/location` package contains code that is used to compose the descriptor into a single piece, resolving all
references. This is done using navigation via URI or GIT.

#### Exceptions

All exceptions that can be thrown from the library code are found in this package.

#### Api

The `/api` package contains the code to analyze the `api` fields that are present inside the Data Product Descriptor.
These can be written following `AsyncAPI`, `OpenAPI`, or `DataStoreAPI` specifications.

## Blueprint lineage (`blueprint`)

The optional root-level `blueprint` object records **provenance** when a data product is created by instantiating a platform blueprint. It answers two questions for tools and operators:

1. **Which blueprint version** produced this descriptor?
2. **Which parameter values** were applied at instantiation (including manifest defaults merged into the resolved map)?

The block is **not** part of the blueprint manifest itself; it is embedded in the **data product descriptor** on the root target repository after templating and before the descriptor is committed. Descriptors that were never instantiated from a blueprint simply omit `blueprint`, and parsers treat the field as optional.

### Model

- Java type: `org.opendatamesh.dpds.model.blueprint.Blueprint`
- Parent: `DataProductVersion` exposes it via `getblueprint()` / `setblueprint()`
- Visitors: `DataProductVersionVisitor#visit(Blueprint)` is invoked during parse/serialize walks when `blueprint` is present

| Property | Description |
| --- | --- |
| `schemaVersion` | Version of the `blueprint` object shape (currently `"1"`) |
| `blueprintUuid` | Platform identifier of the parent blueprint |
| `blueprintName` | Blueprint technical name |
| `blueprintDisplayName` | Human-readable blueprint title |
| `blueprintVersionUuid` | Platform identifier of the published blueprint version used |
| `blueprintVersionNumber` | Semantic version number of that blueprint version |
| `blueprintVersionTag` | Optional tag associated with the published version |
| `parameters` | JSON object of resolved instantiation parameters (`JsonNode` in Java) |

### Example

```json
{
  "info": {
    "name": "my-data-product",
    "version": "1.0.0"
  },
  "blueprint": {
    "schemaVersion": "1",
    "blueprintUuid": "a1b2c3d4-...",
    "blueprintName": "customer-360",
    "blueprintDisplayName": "Customer 360",
    "blueprintVersionUuid": "e5f6g7h8-...",
    "blueprintVersionNumber": "2.1.0",
    "blueprintVersionTag": "release-2026-05",
    "parameters": {
      "environment": "prod",
      "region": "eu-west-1"
    }
  }
}
```

Typical writers (for example the blueprint platform instantiate use case) populate this block using the shared `Parser` deserialize → mutate → serialize flow so both **JSON and YAML** descriptors round-trip consistently. Consumers can use the stored UUIDs and names to link back to blueprint catalog UIs or to support future governance checks (for example verifying that a data product still matches its originating blueprint and parameters).

## Examples
#### Parsing textual descriptor into DataProductVersionDPDS object
```java
        String descriptorContent = "...";
        DescriptorLocation descriptorLocation = new UriLocation(descriptorContent);
        DPDSParser descriptorParser = new DPDSParser(
                "https://raw.githubusercontent.com/opendatamesh-initiative/odm-specification-dpdescriptor/main/schemas/",
                "1.0.0",
                null
        );
        IdentifierStrategy identifierStrategy = IdentifierStrategyFactory.getDefault("org.opendatamesh");
        ParseOptions options = new ParseOptions();
        options.setServerUrl(serverUrl);
        options.setIdentifierStrategy(identifierStrategy);
        
        ParseResult result = descriptorParser.parse(descriptorLocation, options);
        DataProductVersionDPDS descriptor = result.getDescriptorDocument();
```

#### Parsing textual descriptor into DataProductVersionDPDS object

## Prerequisites

The project requires the following dependencies:

- Java 11
- Maven 3.8.6

## Installation

To install the library in your Maven project, you must:

1. Add the dependency to the `pom.xml` file.

```xml

<dependency>
    <groupId>org.opendatamesh</groupId>
    <artifactId>odm-specification-dpdescriptor-parser</artifactId>
    <version>SELECTED VERSION</version>
</dependency>
```

2. Add the repository to the `pom.xml` file.

```xml

<repositories>
    <repository>
        <id>github</id>
        <name>GitHub Packages</name>
        <url>https://maven.pkg.github.com/opendatamesh-initiative/odm-specification-dpdescriptor-parser</url>
    </repository>
</repositories>
```

If the `mvn install` command is executed outside of a GitHub action (e.g. locally), you need to configure the
Maven `settings.xml` file with your GitHub credentials. The GITHUB TOKEN must have `read:packages` permissions.

```xml

<settings>
    <servers>
        <server>
            <id>github</id>
            <username>GITHUB USERNAME</username>
            <password>GITHUB TOKEN</password>
        </server>
    </servers>
</settings>
```

The `settings.xml` file is in the `~/.m2` directory.

For additional information,
see ["How to install an Apache Maven package from GitHub Packages"](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#installing-a-package).

## Releases

To publish a new release, navigate to the main page of the repository and go to Releases -> Draft a new release.
Select a tag for the release. Note: This tag must match the Maven version specified in the repository's .pom file.
Upon publishing the new release, the GitHub Action will automatically publish the corresponding Maven package to GitHub
Packages.