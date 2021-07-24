# custom validations
## Overview

This repo has custom validations implementation when we need to make one of the field is mandatory only if another field has populed with specific value else it is optional.
In similar way, two field validations.

## Prerequisites

 - Java JDK 8
 - Any java editors
 
 
## IntelliJ Setup
 
 - The project uses Lombok so the Lombok plugin needs to be installed ```Idea Menu > Preferences > Plugins > (Search In Repositories)```
 - Enable the annotations processing in ```Idea Menu > Preferences > Build, Execution, Deployment > Compiler > Annotation Processors```
 
## Build the application

To build the application, run the following command in the root project folder:

Linux and MacOS:

    ./gradlew clean build 
        
Windows:

    gradlew clean build 
    
This will create the application artifact (jar) and a docker image.

## Run the application 

You can bring up the application either by running the jar or by running the docker image. 

### Run the jar

    java -jar <jar_name>