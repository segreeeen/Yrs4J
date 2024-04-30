# Yrs4J
Yrs4J are Java JNA Bindings for [Yrs](https://github.com/y-crdt/y-crdt) . 
It is still W.I.P. and by far not complete yet. 

A very first alpha is available on my Nexus (see below for Repo infos).

# Repository
You need to define the Nexus Repo in your Build Script
## Maven 

    <repositories>
        <repository>
            <url>https://nexus.freie-fantasy-welt.de/repository/libs/</url>
        </repository>
    </repositories>

## Gradle

    repositories {
        maven {
            url "https://nexus.freie-fantasy-welt.de/repository/libs/"
        }
    }    

# Usage
## Native Libraries


## Bindings
To include the bindings in your Maven or Gradle project use this artifact:

    <dependency>
      <groupId>at.yrs4j</groupId>
      <artifactId>bindings</artifactId>
      <version>0.1.0-alpha</version>
    </dependency>
