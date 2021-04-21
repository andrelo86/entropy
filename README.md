# Entropy application setup

## What do you need
 - Java 8+
 - Maven

## Steps to execute
  - Download repository
  - Set java jdk accordingly (8+)
  - Run mvn clean install in project root
  - In the path location of following files, execute: 
        javac EntropyFileCalculator.java 
        javac Entropy.java
  - Run java Entropy "filepath" to process file by default
  - Run java Entropy "blockSize" "filepath" to process file with selected block size (i.e: "500")
  - Run EntropyTest to execute unit tests
