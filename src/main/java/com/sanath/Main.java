package com.sanath;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Data path missing");
            System.exit(1);
        }
        String dataFilePath = args[0];
        try {
            List<Word> inputWords = DataLoader.readDataFile(dataFilePath);
            SuggestionEngine engine = new SuggestionEngine(inputWords);

            String query = promptUser();

            while (query != null && !query.isEmpty()) {
                List<Word> result = engine.search(query);
                result.forEach(System.out::println);
                System.out.println();
                query = promptUser();
            }

            System.out.println("Goodbye");
        } catch (FileNotFoundException e) {
            System.out.println("Invalid file");
        }
    }

    private static String promptUser() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        System.out.print("Enter search query: ");
        return scanner.nextLine();
    }
}
