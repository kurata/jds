# JDS - Java domain service

* [What is JDS?](#what-is-jds)
* [Requirements](#requirements)
* [Using JDS](#using-jds)
 * [Maven](#maven)
* [Documentation and getting help](#documentation-and-getting-help)
* [Links](#links)

## What is JDS?

JDS is a Java framework for generate a simple REST service to retrieve Enum Domains values. It saves you from writing one or two dummy services by microservice. The framework comes with generic defaults but enable you to define and configure the services and mappings.

To create a service, declare a simple domain enum like this:
```
@SimpleDomain
public enum StatusType {

    OPENED, CLOSED, IN_PROGRESS

}
```

## Requirements

JDS requires Java 1.8 or later.

## Using JDS

JDS use a command line builds to generate 

### Maven

For Maven-based projects, add the following to you POM file in order to use JDS.

```xml
...
<properties>
    <jds.version>0.0.1</jds.version>
</properties>
...
<dependencies>
    <dependency>
        <groupId>br.com.aqueteron</groupId>
        <artifactId>jds-core</artifactId>
        <version>${jds.version}</version>
    </dependency>
</dependencies>
...
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.11.0</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
                <annotationProcessorPaths>
                    <path>
                        <groupId>br.com.aqueteron</groupId>
                        <artifactId>jds-core</artifactId>
                        <version>${jds.version}</version>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>
    </plugins>
</build>

```

## Documentation and getting help

To learn more about JDS.

## Links

* [Source code](https://github.com/kurata/jds)

## Licensing


