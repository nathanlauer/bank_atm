package com.bank.atm.util;

/**
 * Formatting utils
 */
public class Formatter {

    /**
     * https://stackoverflow.com/questions/2559759/how-do-i-convert-camelcase-into-human-readable-names-in-java
     * Places a space between each camelcase word
     * @param s - string to modify
     * @return
     */
    public static String splitCamelCase(String s) {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }
}
