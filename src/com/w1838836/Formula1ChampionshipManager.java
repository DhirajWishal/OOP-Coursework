package com.w1838836;

import java.io.*;
import java.sql.Time;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager, Serializable {
    private int mNumberOfCars;
    private int mNumberOfDrivers;
    private ArrayList<Formula1Driver> mDrivers = new ArrayList<>();
    private ArrayList<Race> mRaces = new ArrayList<>();
    private HashSet<Integer> mRaceIDs = new HashSet<>();
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
     * Utility function to print a separator.
     */
    public static void printSeparator() {
        System.out.println("--------------------------------------------------");
    }

    /**
     * Display the menu.
     */
    @Override
    public void showMenu() {
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
    @Override
    public int getCommand() {
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
                System.out.println("Wrong command! Please enter a command specified by the menu.");
            }
            showMenu();
        }
    }

    /**
     * Create a new driver and add to the championship.
     */
    @Override
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
        mNumberOfDrivers++;
        mNumberOfCars++;
    }

    /**
     * Display information about all the registered drivers.
     */
    @Override
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
    @Override
    public void deleteDriver() {
        System.out.println("Delete driver option.");
        displayDrivers();

        System.out.print("Enter the index of the driver to delete: ");
        try {
            int index = Integer.parseInt(mScanner.nextLine());

            // Validate the index and remove the driver.
            if (index >= 0 && index < mDrivers.size()) {
                mDrivers.remove(index);

                mNumberOfDrivers--;
                mNumberOfCars--;

                System.out.println("Driver removed!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid driver index!");
        }
    }

    /**
     * Change the driver of a given team.
     */
    @Override
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
    @Override
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
    @Override
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
     * Generate a new and unique race ID.
     *
     * @return The generated race ID.
     */
    private int generateRaceID() {
        int raceID = mRandom.nextInt();
        if (!mRaceIDs.add(raceID))
            raceID = mRandom.nextInt();

        return raceID;
    }

    /**
     * Add a new race.
     * All the data are generated automatically.
     *
     * @param bStatus The status of the race. If true, it is inserted using the CMD, else its generated.
     */
    @Override
    public void addRace(boolean bStatus) {
        mRandom.setSeed((new Date().getTime()));
        int raceID = generateRaceID();

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

            driver.setRaceInfo(raceID, number);
        }

        // Add this race.
        if (bStatus)
            mRaces.add(new Race(raceID, new ArrayList<Driver>(mDrivers), Race.RaceStatus.INSERTED, new Date()));
        else
            mRaces.add(new Race(raceID, new ArrayList<Driver>(mDrivers), Race.RaceStatus.GENERATED, new Date()));
    }

    /**
     * Get the probability to win for a given position.
     *
     * @param position The position of the driver.
     * @return The winning probability.
     */
    private double getProbability(int position) {
        double probability = 0;
        switch (position) {
            case 1 -> probability = 0.4;
            case 2 -> probability = 0.3;
            case 3, 4 -> probability = 0.1;
            case 5, 6, 7, 8, 9 -> probability = 0.2;
        }

        return probability;
    }

    /**
     * Check the probability.
     * Here we do this by running a for loop for 100 * probability times, and checking with a random integer, which will give
     * us a probabilistic result.
     *
     * @param probability The probability to check.
     * @return Whether a probability was successful.
     */
    private boolean checkProbability(double probability) {
        int randomNumber = mRandom.nextInt(0, 100);
        for (int i = 0; i < (int) probability * 100; i++)
            if (i == randomNumber)
                return true;

        return false;
    }

    /**
     * Add a race probabilistically.
     * The drivers will have a random staring position and depending on it, their winning positions are calculated probabilistically.
     * <p>
     * The algorithm works by first assigning each driver his/her starting position, and their respective probability of winning. These
     * drivers are then added to a container and stored to compete. Then later we test each driver's probability of winning using another
     * random number. The first driver to win will be given the first position. The second will be given the second position and so on.
     * If no driver could win, the driver with the highest probability to win will be selected as the winner in the round.
     */
    @Override
    public void addRaceProbabilistically() {
        mRandom.setSeed((new Date().getTime()));

        int raceID = generateRaceID();
        HashSet<Integer> uniquePositions = new HashSet<>();
        ArrayList<ProbabilityContainer> probabilistic = new ArrayList<>();

        // Get the positions of all the drivers.
        for (int i = 0; i < mDrivers.size(); i++) {
            int startingPos = mRandom.nextInt(1, mDrivers.size());
            while (!uniquePositions.add(startingPos))
                startingPos = mRandom.nextInt(1, mDrivers.size() + 1);

            probabilistic.add(new ProbabilityContainer(mDrivers.get(i), i, getProbability(startingPos)));
        }

        // Iteratively compete.
        int position = 1;
        boolean bSomeoneWon;
        while (!probabilistic.isEmpty()) {
            bSomeoneWon = false;
            for (ProbabilityContainer container : probabilistic) {
                // Check if the driver won.
                if (checkProbability(container.getProbability())) {
                    Formula1Driver driver = (Formula1Driver) container.getDriver();

                    driver.updatePoints(position);
                    driver.incrementNumberOfRaces();

                    // Increment the position wins if its within 1 - 3.
                    if (position < 4)
                        driver.incrementPositionWin(position - 1);

                    driver.setRaceInfo(raceID, position);
                    position++;

                    // Remove the object from the array list.
                    probabilistic.remove(container);
                    bSomeoneWon = true;
                    break;
                }
            }

            // If no one won, insert the most probable driver as the winner in this round.
            if (!bSomeoneWon) {
                ProbabilityContainer maximum = new ProbabilityContainer();

                // Find the maximum.
                for (ProbabilityContainer container : probabilistic) {
                    if (maximum.compareTo(container) < 0)
                        maximum = container;
                }

                // Take the maximum driver as the winner.
                Formula1Driver driver = (Formula1Driver) maximum.getDriver();

                driver.updatePoints(position);
                driver.incrementNumberOfRaces();

                // Increment the position wins if its within 1 - 3.
                if (position < 4)
                    driver.incrementPositionWin(position - 1);

                driver.setRaceInfo(raceID, position);
                position++;

                // Remove the object from the array list.
                probabilistic.remove(maximum);
            }
        }

        // Add this race.
        mRaces.add(new Race(raceID, new ArrayList<>(mDrivers), Race.RaceStatus.GENERATED, new Date()));
    }

    /**
     * Save the championship data to an external file.
     * For this we use serialization.
     *
     * @param bShouldWarn Whether to warn the user if there was an error.
     */
    @Override
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
            serializeStream.writeObject(mRaces);
            serializeStream.flush();
            serializeStream.writeObject(mRaceIDs);
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
    @Override
    public void loadData(boolean bShouldWarn) {
        try {
            FileInputStream inputStream = new FileInputStream("Serialize/Formula1.bin");
            ObjectInputStream serializeStream = new ObjectInputStream(inputStream);

            mNumberOfCars = serializeStream.read();
            mNumberOfDrivers = serializeStream.read();

            mDrivers = (ArrayList<Formula1Driver>) serializeStream.readObject();
            mRaces = (ArrayList<Race>) serializeStream.readObject();
            mRaceIDs = (HashSet<Integer>) serializeStream.readObject();

            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            if (bShouldWarn)
                System.out.println("Failed to load data!");
        }
    }

    /**
     * Pack the array list content to a 2D raw array.
     *
     * @param bShouldSort Whether to sort and store the array.
     * @return The packed 2D array.
     */
    @Override
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

    /**
     * Get the races of this season.
     *
     * @return The races.
     */
    @Override
    public ArrayList<Race> getRaces() {
        return mRaces;
    }

    /**
     * Convert the race data into a 2D string.
     *
     * @return The converted 2D string.
     */
    @Override
    public String[][] raceToArray() {
        // Create and sort the array.
        ArrayList<Race> list = new ArrayList<>(mRaces);
        list.sort(Collections.reverseOrder());

        String[][] array = new String[mRaces.size()][2];
        for (int i = 0; i < list.size(); i++) {
            Race race = mRaces.get(i);

            array[i][0] = race.getDate().toString();
            array[i][1] = race.getStatus().toString();
        }

        return array;
    }
}
