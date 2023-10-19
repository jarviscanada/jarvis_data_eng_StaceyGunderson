package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp {

    public static void main(String[] args) {
        if (args.length != 3) {

            JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
            javaGrepLambdaImp.setRegex(args[0]);
            javaGrepLambdaImp.setRootPath(args[1]);
            javaGrepLambdaImp.setOutFile(args[2]);

            try {
                javaGrepLambdaImp.process();
            } catch (Exception e) {
                System.err.println("Error");
            }
        }
    }

    /**
     * Lambda and stream APIs
     * @param inputFile
     * @return
     */
    @Override
    public List<String> readLines(File inputFile) {
        List<String> lines = new ArrayList<String>();
        File file = new File(String.valueOf(inputFile));

        try (Stream<String> stream = Files.lines(inputFile.toPath())) {
            stream.forEach(streamline -> {
                lines.add(streamline);
            });
        } catch (IOException e) {
            System.err.println("Error with input file '" + inputFile + "' :" + e.getMessage());
        }


        return lines;
    }

    /**
     * Lambda and stream APIs
     * @param rootDir
     * @return
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

}