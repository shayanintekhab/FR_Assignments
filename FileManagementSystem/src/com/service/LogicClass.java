package com.service;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogicClass {
    // in‑memory "files"
    private List<String> fileList = new ArrayList<>();
    
    private Scanner scanner = new Scanner(System.in);

    public void listAllFiles() {
        if (fileList.isEmpty()) {
            System.out.println("No files in the directory.\n");
            return;
        }

        List<String> sorted = new ArrayList<>(fileList);
        Collections.sort(sorted, String.CASE_INSENSITIVE_ORDER);

        System.out.println("Files in ascending order:");
        for (String name : sorted) {
            System.out.println("  " + name);
        }
        System.out.println();
    }

    public void businessOperationsMenu() {
        while (true) {
            System.out.println("Business Operations:");
            System.out.println("A. Add a file");
            System.out.println("B. Delete a file");
            System.out.println("C. Search for a file");
            System.out.println("D. Back to Main Menu");
            System.out.print("Select an option (A–D): ");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    addFile();
                    break;
                case "B":
                    deleteFile();
                    break;
                case "C":
                    searchFile();
                    break;
                case "D":
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter A, B, C or D.\n");
            }
        }
    }

    private void addFile() {
        System.out.print("Enter filename to add: ");
        String filename = scanner.nextLine().trim();

        boolean exists = fileList.stream()
                                 .anyMatch(f -> f.equalsIgnoreCase(filename));
        if (exists) {
            System.out.println("A file with that name already exists.\n");
        } else {
            fileList.add(filename);
            System.out.println("File '" + filename + "' added successfully.\n");
        }
    }

    public void deleteFile() {
        System.out.print("Enter exact filename to delete: ");
        String filename = scanner.nextLine().trim();

        if (fileList.remove(filename)) {
            System.out.println("File '" + filename + "' deleted successfully.\n");
        } else {
            System.out.println("File not found: " + filename + "\n");
        }
    }

    public void searchFile() {
        System.out.print("Enter filename to search: ");
        String filename = scanner.nextLine().trim();

        if (fileList.contains(filename)) {
            System.out.println("File found: " + filename + "\n");
        } else {
            System.out.println("File not found: " + filename + "\n");
        }
    }
}
