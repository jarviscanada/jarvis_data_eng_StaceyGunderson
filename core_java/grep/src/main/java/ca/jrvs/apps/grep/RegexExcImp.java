package ca.jrvs.apps.grep;

import java.util.regex.*;
public class RegexExcImp implements RegexExc {

    public static void main(String[] args) {
        RegexExcImp regex = new RegexExcImp();
        System.out.println(regex.isEmptyLine("      "));
        System.out.println(regex.isEmptyLine("          "));
        System.out.println(regex.isEmptyLine(""));
        System.out.println(regex.isEmptyLine("a"));
    }

    /**
     * Checks if a given file is a jpeg or jpg file
     * @param filename
     * @return true if it is a jpg or jpeg file
     */
    @Override
    public boolean matchJpeg(String filename) {
        if(Pattern.matches("^[a-zA-z ]+.(jpg|jpeg)$", filename)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if a given line is a valid ip address of type 0.0.0.0 to 999.999.999.999
     * @param ip
     * @return true if ip is valid
     */
    @Override
    public boolean matchIp(String ip) {
        if(Pattern.matches("^[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}$", ip)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if a given line is empty string
     * @param line
     * @return true if line is empty
     */
    @Override
    public boolean isEmptyLine(String line) {
        if(Pattern.matches("^\\s*$", line)) {
            return true;
        }
        return false;
    }
}
