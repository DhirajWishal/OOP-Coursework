package com.w1838836;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Formula1ChampionshipManager implements ChampionshipManager {
    private int mNumberOfCars;
    private int mNumberOfDrivers;
    private ArrayList<Formula1Driver> mDrivers = new ArrayList<>();
    private static Scanner mScanner = new Scanner(System.in);

    /**
     * Get the number of cars.
     *
     * @return The number of cars.
     */
    public int getNumberOfCars() {
        return mNumberOfCars;
    }

    /**
     * Set the number of cars in the manager.
     *
     * @param numberOfCars The number of cars to set.
     */
    public void setNumberOfCars(int numberOfCars) {
        mNumberOfCars = numberOfCars;
    }

    /**
     * Get the number of drivers.
     *
     * @return The number of drivers.
     */
    public int getNumberOfDrivers() {
        return mNumberOfDrivers;
    }

    /**
     * Set the number of drivers in the manager.
     *
     * @param numberOfDrivers The number of drivers to set.
     */
    public void setNumberOfDrivers(int numberOfDrivers) {
        mNumberOfDrivers = numberOfDrivers;
    }

    /**
     * Run the championship.
     */
    public static void run() {
        Formula1ChampionshipManager manager = new Formula1ChampionshipManager();

        while (true) {
            showMenu();
            Integer command = getCommand();

            switch (command) {
                case 1:
                    manager.createNewDriver();
                    break;

                case 2:
                    manager.deleteDriver();
                    break;

                case 3:
                    manager.changeDriver();
                    break;

                case 4:
                    manager.displayStatistics();
                    break;
            }
        }
    }

    /**
     * Display the menu.
     */
    public static void showMenu() {
        System.out.println("--------------------------------------------------");
        System.out.println("Formula 1 Championship Manager");
        System.out.println("Format: [Command]. [Description]");
        System.out.println();
        System.out.println("1. Create a new driver.");
        System.out.println("2. Delete a driver.");
        System.out.println("3. Change the driver for an existing constructor team.");
        System.out.println("4. Display driver statistics.");
        System.out.println("5. Display driver table.");
        System.out.println("6. Add a new race.");
        System.out.println("7. Save data.");
        System.out.println("8. Load data.");
        System.out.println("--------------------------------------------------");
    }

    /**
     * Get a validated command from the user.
     *
     * @return The command number.
     */
    public static Integer getCommand() {
        while (true) {
            try {
                System.out.print("Enter command: ");
                Integer command = Integer.parseInt(mScanner.nextLine());

                // Validate the command and return if true.
                if (command < 9 && command > 0)
                    return command;
            } catch (NumberFormatException e) {
            }

            System.out.println("Wrong command! Please enter a command specified by the menu.");
            showMenu();
        }
    }

    /**
     * Create a new driver and add to the championship.
     */
    public void createNewDriver() {
        System.out.print("Enter the driver name: ");
        String name = mScanner.nextLine();

        System.out.print("Enter the driver location: ");
        String location = mScanner.nextLine();

        System.out.print("Enter the driver team: ");
        String team = mScanner.nextLine();

        // Check if a driver exists for the given team.
        for (Formula1Driver driver : mDrivers) {
            if (driver.getTeam().compareToIgnoreCase(team) == 0) {
                System.out.println("A driver already exists! Make sure that the team is unique.");
                return;
            }
        }

        mDrivers.add(new Formula1Driver(name, location, team));
    }

    /**
     * Display information about all the registered drivers.
     */
    public void displayDrivers() {
        System.out.println("Index\tName\tLocation\tTeam");
        for (int i = 0; i < mDrivers.size(); i++) {
            Formula1Driver driver = mDrivers.get(i);

            System.out.print(i + "\t");
            System.out.print(driver.getName() + "\t");
            System.out.print(driver.getLocation() + "\t");
            System.out.print(driver.getTeam() + "\t");
            System.out.println();
        }
    }

    /**
     * Delete a driver from the manager.
     */
    public void deleteDriver() {
        displayDrivers();

        System.out.print("Enter the index of the driver to delete: ");
        try {
            int index = Integer.parseInt(mScanner.nextLine());

            // Validate the index and remove the driver.
            if (index >= 0 && index < mDrivers.size()) {
                mDrivers.remove(index);
                System.out.println("Driver removed!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid driver index!");
        }
    }

    /**
     * Change the driver of a given team.
     */
    public void changeDriver() {
        displayDrivers();

        System.out.print("Enter the index of the driver to change the team: ");
        try {
            int index = Integer.parseInt(mScanner.nextLine());

            // Validate the index and remove the driver.
            if (index >= 0 && index < mDrivers.size()) {
                System.out.print("Enter the name of the new driver: ");
                mDrivers.get(index).setName(mScanner.nextLine());

                System.out.print("Enter the location of the new driver: ");
                mDrivers.get(index).setLocation(mScanner.nextLine());

                System.out.println("Successfully changed the driver!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid driver index!");
        }
    }

    /**
     * Display the statistics of a selected driver.
     */
    public void displayStatistics() {
        displayDrivers();

        System.out.print("Enter the index of the driver to show the statistics: ");
        try {
            int index = Integer.parseInt(mScanner.nextLine());

            // Validate the index and remove the driver.
            if (index >= 0 && index < mDrivers.size()) {
                // Get the formula 1 driver.
                Formula1Driver driver = (Formula1Driver) mDrivers.get(index);

                System.out.println("Driver index:                " + index);
                System.out.println("Driver name:                 " + driver.getName());
                System.out.println("Driver location:             " + driver.getLocation());
                System.out.println("Driver team:                 " + driver.getTeam());
                System.out.println("Number of points won:        " + driver.getPoints());
                System.out.println("Number of races won:         " + driver.getNumberOfRaces());
                System.out.println("Number of first places won:  " + driver.getPositionsWon()[Formula1Driver.FIRST_POSITION]);
                System.out.println("Number of second places won: " + driver.getPositionsWon()[Formula1Driver.SECOND_POSITION]);
                System.out.println("Number of third places won:  " + driver.getPositionsWon()[Formula1Driver.THIRD_POSITION]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid driver index!");
        }
    }
}
