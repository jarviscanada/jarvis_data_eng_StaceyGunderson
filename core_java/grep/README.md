# Java Grep App

# Introduction
The Java grep app and regex matcher implements a respectively matching interface to copy their respective grep functions from linux and regex matching for specific patterns (ip, jpg files, empty . 
Developed in IntelliJ Ultimate IDE, this app uses the java and library such as slf4j for logging and Maven to build and compile code.
This can be run to check recursively within files for a set pattern, or to check if a given line matches a specific pattern.


# Quick Start
How to use your apps?

After deploying or running the app, an example command such as below:

java -cp (name of file).jar ca.jrvs.apps.grep.JavaGrepImp (regex pattern) (input folder) (output file)

java -Xms5m -Xmx5m 
-cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp 
.*Romeo.*Juliet.* ./data ./out/grep.txt


# Implemenation
## Pseudocode
The processing method does the following: 
    
    Gets the list of all files within the given file path, recursively
    For each file, get the lines in the given file
    Checks each line with a regex check for the given pattern
    Adds the line to memory if it matches
    After all files, prints the lines to the given output folder


## Performance Issue    

The issue with memory is it does not utilize a buffered reader, so files larger than the heap memory can allow will cause the program to crash.
Buffered reading or streaming would fix this problem.

    

# Testing

RegexExc and JavaGrep were both tested manually, test cases were created with edge cases to test the validity of the returned result.
JavaGrep was given test files within recursive files to output to a test files with dummy text files.   

slf4j was added as a logger for errors.


# Deployment
How did you dockerize your app for easier distribution?

    cd core_java/grep
    docker_user=your_docker_id
    docker login -u ${docker_user} --password-stdin
    
    #Create dockerfile (make sure you understand all commands)
    cat > Dockerfile << EOF
    FROM openjdk:8-alpine
    COPY target/grep*.jar /usr/local/app/grep/lib/grep.jar
    ENTRYPOINT ["java","-jar","/usr/local/app/grep/lib/grep.jar"]
    EOF
    
    #Package your java app
    mvn clean package
    #build a new docker image locally
    docker build -t ${docker_user}/grep .
    #verify your image
    docker image ls | grep "grep"
    #run docker container (you must undertand all options)
    docker run --rm \
    -v `pwd`/data:/data -v `pwd`/log:/log \
    ${docker_user}/grep .*Romeo.*Juliet.* /data /log/grep.out
    
    #push image to Docker Hub
    docker push ${docker_user}/grep


# Improvements

One improvement would be to stream from the file instead of storing it in memory, this would help with heap overflow and storage usage.

Another improvement would be to write lines to the file as they are read, to save on memory usage storing all the lines found.

Finally, another better usage would be to introduce better and more specific logging for errors.