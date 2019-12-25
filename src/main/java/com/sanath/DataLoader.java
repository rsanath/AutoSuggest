package com.sanath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class DataLoader {
    private static final String SEPARATOR = ",";

    public static List<Word> readDataFile(String path) throws FileNotFoundException {
        List<Word> words = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNext()) {
                Word word = buildWord(scanner.nextLine());
                words.add(word);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid data file");
        }

        return words;
    }

    private static Word buildWord(String row) {
        String[] values = row.split(SEPARATOR);

        String word = values[0];
        int weight = Integer.parseInt(values[1]);

        return new Word(word, weight);
    }
}
