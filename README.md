# SIDCDownloader
A Java-based tool for downloading solar drawings from the [Solar Influences Data Analysis Center (SIDC)](https://www.sidc.be/) website.

![Java](https://img.shields.io/badge/Java-21-blue)
![Release](https://img.shields.io/github/v/release/Kermut572/SIDCDownloaderJava)

## Description
SIDCDownloader is a lightweight and efficient Java tool designed to automate the downloading of solar drawings from the [SIDC website](https://www.sidc.be/). 

### Key Features
- **Customizable Date Range:** Download images from a specified starting year to an ending year.
- **Multithreading Support:** Accelerates downloading by utilizing multiple threads.
- **Simple and Portable:** Available as a precompiled JAR for ease of use.

## Installation
There are two options for installing the project:

### Option 1
1. Clone the repository:
   ```git clone https://github.com/Kermut572/SIDCDownloaderJava.git```
2. Build using Maven

### Option 2
Download the latest version from the [Releases Page](https://github.com/Kermut572/SIDCDownloaderJava/releases).

## Usage
Run the program from the command line with the following syntax:
```java -jar SIDCDownloader.jar <start_year> <end_year> [nb_threads]```
