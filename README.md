# Yrs4J
Yrs4J are Java JNA Bindings for [Yrs](https://github.com/y-crdt/y-crdt) . 
It is still W.I.P. and by far not complete yet. 

A very first alpha is available on my Nexus (see below for Repo infos).

This project is split up into 4 subprojects: 
- **yrs4j-bindings** - This subproject provides JNA Java bindings and a Java wrapper layer, simplifying integration and enhancing usability for Java developers. The bindings create a direct interface with the native library, while the wrapper layer abstracts the complexities of native calls into more manageable, object-oriented Java methods.
- **yrs4j-native-windows** - This subproject includes the Windows-specific native libraries
- **yrs4j-native-linux** - This subproject houses the Linux-specific native libraries
- **yrs4j-native-mac** (not supported yet) - This subproject is planned to include the macOS-specific native libraries

# Usage
To use Yrs4J add a dependency for the Bindings and for the Native-Lib (may be multiple) your code should run on (see *Artifacts & Repository* section below) . 

    implementation 'at.yrs4j:bindings:0.1.0-alpha'
    implementation 'at.yrs4j:libnative-windows:0.1.0-alpha'

You then can use the bindings like this:

    Yrs4J.init(WindowsLibLoader.create());

Analogous to WindowsLibLoader, if you use the Linux Bindings use LinuxLibLoader or for Mac (later) MacLibLoader (**not yet implemented**).

After initialization you can then either use the wrapper classes (not complete as of 01.05.2024) or the native JNA interface. 

If you want to use the Wrapper, make sure to have a look at the [examples project](https://github.com/segreeeen/Yrs4J/blob/main/yrs4j-examples/src/main/java/at/yrs4j/example/Main.java). There are some of the examples/tests provided by [Yrs](https://github.com/y-crdt/y-crdt) implemented in Yrs4J.

If you feel more comfortable using the JNA interface just use the native interface. You can get use the instance of the JNA bindings like this:

    Yrs4J.YRS_INSTANCE.someNativeMethod(...)

As of now you may have to do that anyway since some of the functionality is not implemented as wrapper yet. You can also extend the wrapper layer by extending [AbstractJNAWrapper](https://github.com/segreeeen/Yrs4J/blob/main/yrs4j-bindings/src/main/java/at/yrs4j/wrapper/AbstractJNAWrapper.java).

# Artifacts & Repository

## Repository
You need to define the repo in your build script to use the dependencies

    <repositories>
        <repository>
            <url>https://nexus.freie-fantasy-welt.de/repository/libs/</url>
        </repository>
    </repositories>

## Bindings

    <dependency>
        <groupId>at.yrs4j</groupId>
        <artifactId>bindings</artifactId>
        <version>0.1.0-alpha</version>
    </dependency>

## Native Libs

### Linux

    <dependency>
        <groupId>at.yrs4j</groupId>
        <artifactId>libnative-linux</artifactId>
        <version>0.1.0-alpha</version>
    </dependency>

### Windows
    <dependency>
        <groupId>at.yrs4j</groupId>
        <artifactId>libnative-windows</artifactId>
        <version>0.1.0-alpha</version>
    </dependency>

### Mac
... Coming soon ...

# Development

## Building from Source
In the root repository execute 

In windows:

    gradlew.bat build

In linux:

     ./gradlew build

to build all the subprojects. Then use yrs4j-examples as a testing ground for new developments.
    
To test your changes with other projects you should be able to use your local maven repo by executing 

    gradlew publishToMavenLocal

The maven packages should then be available in your local repository. You may have to change the version of the project you modified in the gradle.properties file, so that gradle recognizes the new version.

