package com.sanath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataLoader {
    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    public static List<Word> readDataFile(String path) throws FileNotFoundException {
        List<Word> words = new ArrayList<>();

        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNext()) {
            List<String> line = parseLine(scanner.nextLine());
            String value = line.get(0);
            int weight = Integer.parseInt(line.get(1));
            Word word = new Word(value, weight);
            words.add(word);
        }
        scanner.close();

        return words;
    }

    private static List<String> parseLine(String cvsLine) {
        List<String> result = new ArrayList<>();
        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {
            if (inQuotes) {
                startCollectChar = true;
                if (ch == DEFAULT_QUOTE) {
                    inQuotes = false;
                } else {
                    //Fixed : allow "" in custom quote enclosed
                    curVal.append(ch);

                }
            } else {
                if (ch == DEFAULT_QUOTE) {
                    inQuotes = true;
                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"') {
                        curVal.append('"');
                    }
                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }
                } else if (ch == DEFAULT_SEPARATOR) {
                    result.add(curVal.toString());
                    curVal = new StringBuffer();
                    startCollectChar = false;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }
        }
        result.add(curVal.toString());
        return result;
    }
}
