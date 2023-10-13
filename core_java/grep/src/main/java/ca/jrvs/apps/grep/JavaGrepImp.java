package ca.jrvs.apps.grep;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;
import org.slf4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.LoggerFactory;

public class JavaGrepImp implements JavaGrep {

    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRootPath() {
        return rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getOutFile() {
        return outFile;
    }

    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

    private String rootPath;
    private String outFile;
    private String regex;

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile.");
        }

        //Default logging config
        //BasicConfigurator.configure();

        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try {
            javaGrepImp.process();
        } catch (Exception e) {
            javaGrepImp.logger.error("Error: Unable to process", e);
        }
    }

    /**
     * Processes all files in the folder for a specific regex akin to a grep command
     * @throws IOException if error with files
     */
    @Override
    public void process() throws IOException {
        List<File> files = listFiles(rootPath);
        List<String> lines = new ArrayList<String>();
        for (File file : files) {
            List<String> fileLines = readLines(file);
            for (String line : fileLines){
                if (containsPattern(line)){
                    lines.add(line);
                }
            }
        }

        writeToFile(lines);

    }

    /**
     * Lists all files and subfiles in a given directory
     * @param rootDir input directory to list
     * @return a list of all files
     */
    @Override
    public List<File> listFiles(String rootDir) {
        List<File> files = new ArrayList<File>();
        File[] fileList = new File(rootDir).listFiles();

        for (File fileEntry : fileList){
            if (fileEntry.isFile()){
                files.add(fileEntry);
            } else if (fileEntry.isDirectory()){
                files.addAll(listFiles(fileEntry.toString()));
            }
        }

        return files;
    }

    /**
     * Reads lines from a given file
     * @param inputFile file to be read
     * @return the lines in the file
     */
    @Override
    public List<String> readLines(File inputFile) {
        List<String> lines = new ArrayList<String>();
        try {
            Scanner myReader = new Scanner(inputFile);
            while (myReader.hasNextLine()) {
                lines.add(myReader.nextLine());
            }
            myReader.close();
        } catch (IOException e) {
            System.err.println("Error with input file '" + inputFile + "' :" + e.getMessage());
        }
        return lines;
    }

    /**
     * Checks if the line given contains the regex pattern to match
     * @param line input string to check
     * @return true if it matches
     */
    @Override
    public boolean containsPattern(String line) {
        if(Pattern.matches(regex, line)){
            return true;
        }
        return false;
    }

    /**
     * Writes the given lines to a file specified as output
     * @param lines The lines to write
     * @throws IOException Throws IOException if raised.
     */
    @Override
    public void writeToFile(List<String> lines) throws IOException {
        File outputFile = new File(outFile);
        if (outputFile.getParentFile() != null) {
            outputFile.getParentFile().mkdirs();
        }
        outputFile.createNewFile();

        FileWriter writer = new FileWriter(outputFile);

        for (String line:lines) {
            writer.write(line + '\n');
        }
        writer.close();
    }

}
