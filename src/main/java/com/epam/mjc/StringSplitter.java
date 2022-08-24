package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        if (source.length() > 0) {
            String first = delimiters.iterator().next();
            List<String> finalList = new ArrayList<>();
            source = source.trim();
            for (String str : delimiters) {
                source = source.replaceAll(str, first);
            }
            String[] splitList = source.split(first);
            for (String str : splitList) {
                if (!str.isEmpty()) {
                    finalList.add(str);
                }
            }
            return finalList;
        }

        throw new UnsupportedOperationException("You should implement this method.");
    }
}
