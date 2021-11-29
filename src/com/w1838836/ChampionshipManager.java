package com.w1838836;

import java.util.ArrayList;

public interface ChampionshipManager {
    /**
     * Show the championship menu.
     */
    void showMenu();

    /**
     * Get a new command from the user.
     *
     * @return The selected command.
     */
    int getCommand();

    /**
     * Create a new driver.
     */
    void createNewDriver();

    /**
     * Display all the drivers.
     */
    void displayDrivers();

    /**
     * Delete a driver from the championship.
     */
    void deleteDriver();

    /**
     * Change a driver team.
     */
    void changeDriver();

    /**
     * Display driver statistics.
     */
    void displayStatistics();

    /**
     * Display the driver table.
     */
    void displayDriverTable();

    /**
     * Add a new race.
     *
     * @param bStatus The status, if the command comes from the GUI or CMD.
     */
    void addRace(boolean bStatus);

    /**
     * Add a new probabilistically won race.
     */
    void addRaceProbabilistically();

    /**
     * Save data to a local file.
     *
     * @param bShouldWarn Whether to show the user if there was an error.
     */
    void saveData(boolean bShouldWarn);

    /**
     * Load the application data to a local file.
     *
     * @param bShouldWarn Whether to show the user if there was an error.
     */
    void loadData(boolean bShouldWarn);

    /**
     * Convert the data to a 2D array.
     *
     * @param bShouldSort Whether to sort the data.
     * @return The dumped array.
     */
    String[][] toArray(boolean bShouldSort);

    /**
     * Get all the stored races.
     *
     * @return The races.
     */
    ArrayList<Race> getRaces();

    /**
     * Covert the race data to a 2D array.
     *
     * @return The dumped array.
     */
    String[][] raceToArray();
}
