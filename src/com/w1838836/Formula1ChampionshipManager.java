package com.w1838836;

import java.io.*;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager, Serializable {
    private int mNumberOfCars;
    private int mNumberOfDrivers;
    private ArrayList<Formula1Driver> mDrivers = new ArrayList<>();
    private Random mRandom = new Random();
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

        // Load the serialized data if possible.
        manager.loadData(false);

        boolean bShouldRun = true;
        while (bShouldRun) {
            showMenu();
            Integer command = getCommand();

            switch (command) {
                case 1 -> manager.createNewDriver();
                case 2 -> manager.deleteDriver();
                case 3 -> manager.changeDriver();
                case 4 -> manager.displayStatistics();
                case 5 -> manager.displayDriverTable();
                case 6 -> manager.addRace();
                case 7 -> manager.saveData(true);
                case 8 -> manager.loadData(true);
                case 9 -> bShouldRun = false;
            }
        }

        System.out.println("Exiting the application..");
        manager.saveData(false);
        System.out.println("Thank you!");
    }


    /**
     * Utility function to print a separator.
     */
    public static void printSeparator() {
        System.out.println("--------------------------------------------------");
    }

    /**
     * Display the menu.
     */
    public static void showMenu() {
        printSeparator();
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
        System.out.println("9. Exit program.");
        printSeparator();
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
                int command = Integer.parseInt(mScanner.nextLine());

                // Validate the command and return if true.
                if (command > 0 && command < 10) {
                    printSeparator();
                    return command;
                }
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
        System.out.println("Create a new driver option.");

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
        System.out.println("Displaying drivers.");
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
        System.out.println("Delete driver option.");
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
        System.out.println("Change driver option.");
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
        System.out.println("Displaying statistics.");
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

    /**
     * Display the driver table.
     */
    public void displayDriverTable() {
        System.out.println("Displaying driver table.");
        printSeparator();

        // Copy the driver information to a new array and sort it.
        ArrayList<Formula1Driver> list = new ArrayList<>(mDrivers);
        list.sort(Collections.reverseOrder());

        // Print some driver statistics.
        for (Formula1Driver driver : list) {
            printSeparator();
            System.out.println("Driver name:                 " + driver.getName());
            System.out.println("Driver location:             " + driver.getLocation());
            System.out.println("Driver team:                 " + driver.getTeam());
            System.out.println("Number of points won:        " + driver.getPoints());
            System.out.println("Number of first places won:  " + driver.getPositionsWon()[Formula1Driver.FIRST_POSITION]);
            printSeparator();
        }
    }

    /**
     * Add a new race.
     * All the data are generated automatically.
     */
    public void addRace() {
        System.out.println("Adding a new race...");

        HashSet<Integer> uniquePositions = new HashSet<>();
        for (Formula1Driver driver : mDrivers) {
            int number = mRandom.nextInt(1, mDrivers.size());
            while (!uniquePositions.add(number))
                number = mRandom.nextInt(1, mDrivers.size() + 1);

            driver.updatePoints(number);
            driver.incrementNumberOfRaces();

            // Increment the position wins if its within 1 - 3.
            if (number < 4)
                driver.incrementPositionWin(number - 1);
        }

        System.out.println("Added a new race.");
    }

    /**
     * Save the championship data to an external file.
     * For this we use serialization.
     *
     * @param bShouldWarn Whether to warn the user if there was an error.
     */
    public void saveData(boolean bShouldWarn) {
        try {
            File temporaryFile = new File("Serialize/Formula1.bin");
            temporaryFile.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(temporaryFile, false);
            ObjectOutputStream serializeStream = new ObjectOutputStream(outputStream);

            serializeStream.write(mNumberOfCars);
            serializeStream.flush();
            serializeStream.write(mNumberOfDrivers);
            serializeStream.flush();
            serializeStream.writeObject(mDrivers);
            serializeStream.flush();
            serializeStream.close();

            System.out.println("Data successfully saved.");
        } catch (IOException e) {
            if (bShouldWarn)
                System.out.println("Failed to save data!");
        }
    }

    /**
     * Load the data from the serialized file.
     *
     * @param bShouldWarn Boolean stating whether to warn if loading fails. If true, it will warn the user.
     */
    public void loadData(boolean bShouldWarn) {
        try {
            FileInputStream inputStream = new FileInputStream("Serialize/Formula1.bin");
            ObjectInputStream serializeStream = new ObjectInputStream(inputStream);

            mNumberOfCars = serializeStream.read();
            mNumberOfDrivers = serializeStream.read();

            mDrivers = (ArrayList<Formula1Driver>) serializeStream.readObject();

            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            if (bShouldWarn)
                System.out.println("Failed to save data!");
        }
    }

    /**
     * Pack the array list content to a 2D raw array.
     *
     * @param bShouldSort Whether to sort and store the array.
     * @return The packed 2D array.
     */
    public String[][] toArray(boolean bShouldSort) {
        // Copy the driver data.
        ArrayList<Formula1Driver> list = new ArrayList<>(mDrivers);

        // Sort the array if necessary.
        if (bShouldSort)
            list.sort(Collections.reverseOrder());

        // Create and copy the content to the string array.
        String[][] array = new String[mDrivers.size()][8];
        for (int i = 0; i < list.size(); i++) {
            Formula1Driver driver = list.get(i);

            array[i][0] = driver.getName();
            array[i][1] = driver.getLocation();
            array[i][2] = driver.getTeam();
            array[i][3] = String.valueOf(driver.getPoints());
            array[i][4] = String.valueOf(driver.getPositionsWon()[Formula1Driver.FIRST_POSITION]);
            array[i][5] = String.valueOf(driver.getPositionsWon()[Formula1Driver.SECOND_POSITION]);
            array[i][6] = String.valueOf(driver.getPositionsWon()[Formula1Driver.THIRD_POSITION]);
            array[i][7] = String.valueOf(driver.getNumberOfRaces());
        }

        return array;
    }
}
