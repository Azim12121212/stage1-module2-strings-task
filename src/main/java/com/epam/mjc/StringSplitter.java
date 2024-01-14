package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        //throw new UnsupportedOperationException("You should implement this method.");

        List<String> result = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(source, delimiters.toString());

        while (st.hasMoreTokens()) {
            result.add(st.nextToken());
        }

        return result;
    }
    public static void main(String[] args) {
        String st = "Hello1geeks;2andRyou";
        Collection<String> delimiters;
        delimiters = new ArrayList<String>((Arrays.asList("1", ";", "R")));

        List<String> result = new StringSplitter().splitByDelimiters(st, delimiters);

        for (String s: result) {
            System.out.println(s);
        }
    }
}
