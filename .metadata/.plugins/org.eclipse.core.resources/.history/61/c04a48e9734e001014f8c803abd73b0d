package com.main;

import java.util.Scanner;

import com.service.LogicClass;

/**
 * LockedMe.com Prototype (In‑Memory Version)
 *
 * - Maintains a List<String> of “file names”
 * - List (ascending), Add, Delete, Search
 * - Menu navigation and exit
 */
public class LockedMeApp extends LogicClass{
    
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        LockedMeApp app = new LockedMeApp();
        app.showWelcomeScreen();
        app.mainMenu(app);
    }

    // Welcome & developer info
    private void showWelcomeScreen() {
        System.out.println("========================================");
        System.out.println("       Welcome to LockedMe.com          ");
        System.out.println("       Developer: Md Shayan Intekhab from IITD        ");
        System.out.println("========================================");
        System.out.println("This prototype manages an in‑memory list of file names.");
        System.out.println();
    }

    // Main menu loop
    private void mainMenu(LockedMeApp logic) {
        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. List all files (ascending)");
            System.out.println("2. Business Operations");
            System.out.println("3. Exit");
            System.out.print("Select an option (1–3): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    logic.listAllFiles();
                    break;
                case "2":
                    logic.businessOperationsMenu();
                    break;
                case "3":
                    System.out.println("Exiting application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2 or 3.\n");
            }
        }
    }


}
