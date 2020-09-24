#!/bin/bash

javac -cp '.:./../dependencies/jackson-core-2.10.0.jar:./../dependencies/jackson-annotations-2.10.0.jar:./../dependencies/jackson-databind-2.10.0.jar' *.java

java -cp '.:./../dependencies/jackson-core-2.10.0.jar:./../dependencies/jackson-annotations-2.10.0.jar:./../dependencies/jackson-databind-2.10.0.jar' Test -f ../maze.json
