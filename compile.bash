#!/bin/bash
JAR_DIR='/Users/mwjin/Task/mwjin_test'
CP=$JAR_DIR/commons-lang3-3.5.jar:$JAR_DIR/mysql-connector-java-5.1.40-bin.jar
javac -cp $CP src/*.java
