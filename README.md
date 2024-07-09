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
    <li><a href="#prerequisites">Prerequisites</a></li>
    <li><a href="#installation">Installation</a></li>
  </ol>
</details>

<!-- Overview -->
## Overview
This library is used for reading, writing, and validating Data Product Descriptors as defined by the Open Data Mesh initiative. It provides tools to deserialize data descriptors into Java objects, validate their schemas, and manage descriptor locations, supporting URI and GIT navigation. The library is structured into several packages, each with a specific focus, such as model, parser, location, exceptions, and API.

<!-- USAGE EXAMPLES -->
## Usage
#### Model
The `/model` package contains the structure of the Data Product Descriptor. The root object is `DataProductVersionDPDS`.

#### Parser
The `/parser` package contains all the code that is used to deserialize a Data Product Descriptor and map it into a `DataProductVersionDPDS` object. Inside the package, the `DPDSParser` can be used to:
  - `parse`: Given a `DescriptorLocation` and `ParseOptions`, this method returns a `DataProductVersionDPDS`, which can be easily navigated.
  - `validateSchema`: Given a data product descriptor, it checks that the schema is valid.

#### Location
The `/location` package contains code that is used to compose the descriptor into a single piece, resolving all references. This is done using navigation via URI or GIT.

#### Exceptions
All exceptions that can be thrown from the library code are found in this package.

#### Api
The `/api` package contains the code to analyze the `api` fields that are present inside the Data Product Descriptor. These can be written following `AsyncAPI`, `OpenAPI`, or `DataStoreAPI` specifications.

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

If the `mvn install` command is executed outside of a GitHub action (e.g. locally), you need to configure the Maven `settings.xml` file with your GitHub credentials. The GITHUB TOKEN must have `read:packages` permissions.

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

For additional information, see ["How to install an Apache Maven package from GitHub Packages"](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#installing-a-package).
